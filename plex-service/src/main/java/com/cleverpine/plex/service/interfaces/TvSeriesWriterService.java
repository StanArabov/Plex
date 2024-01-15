package com.cleverpine.plex.service.interfaces;

import com.cleverpine.plex.dto.ReceivedTvSeriesDto;
import com.cleverpine.plex.entity.future.TvSeriesEntity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface TvSeriesWriterService {
    CompletableFuture<List<TvSeriesEntity>> transformTvSeriesAsync(List<ReceivedTvSeriesDto> receivedTvSeriesDtoList);

    CompletableFuture<Void> saveTvSeriesAsync(List<TvSeriesEntity> entities);
}
