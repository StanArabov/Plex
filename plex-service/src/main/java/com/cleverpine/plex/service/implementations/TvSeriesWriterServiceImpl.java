package com.cleverpine.plex.service.implementations;

import com.cleverpine.plex.dto.ReceivedTvSeriesDto;
import com.cleverpine.plex.entity.future.TvSeriesEntity;
import com.cleverpine.plex.mapper.TvSeriesMapper;
import com.cleverpine.plex.repository.future.TvSeriesRepository;
import com.cleverpine.plex.service.interfaces.TvSeriesWriterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
@AllArgsConstructor
public class TvSeriesWriterServiceImpl implements TvSeriesWriterService {
    private final ExecutorService executorService;
    private final TvSeriesMapper tvSeriesMapper;
    private final TvSeriesRepository tvSeriesRepository;
    @Override
    public CompletableFuture<List<TvSeriesEntity>> transformTvSeriesAsync(List<ReceivedTvSeriesDto> receivedTvSeriesDtoList) {
        return CompletableFuture.supplyAsync(() ->
                tvSeriesMapper.receivedTvSeriesDtoListToTvSeriesEntityList(receivedTvSeriesDtoList), executorService);
    }

    @Override
    public CompletableFuture<Void> saveTvSeriesAsync(List<TvSeriesEntity> entities) {
        return CompletableFuture.runAsync(() -> {
            tvSeriesRepository.saveAll(entities);
        }, executorService);
    }
}
