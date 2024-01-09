package com.cleverpine.plex.service.implementations;

import com.cleverpine.plex.dto.TvSeriesDto;
import com.cleverpine.plex.entity.future.TvSeriesEntity;
import com.cleverpine.plex.mapper.TvSeriesMapper;
import com.cleverpine.plex.repository.future.TvSeriesRepository;
import com.cleverpine.plex.service.interfaces.TvSeriesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TvSeriesServiceImpl implements TvSeriesService {
    private final TvSeriesRepository tvSeriesRepository;
    private final TvSeriesMapper tvSeriesMapper;
    @Override
    public List<TvSeriesDto> getTvSeriesList(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<TvSeriesEntity> tvSeriesPage = tvSeriesRepository.findAll(pageRequest);
        return tvSeriesMapper.tvSeriesEntityListToTvSeriesDtoList(tvSeriesPage.getContent());
    }

    @Override
    public TvSeriesDto getTvSeriesById(Integer tvSeriesId) {
        return tvSeriesRepository.findById(tvSeriesId)
                .map(tvSeriesMapper::tvSeriesEntityToTvSeriesDto)
                .orElseThrow(() -> new RuntimeException("Tv Series not found: " + tvSeriesId));
    }

    @Override
    public TvSeriesDto getTvSeriesByTitle(String tvSeriesTitle) {
        return tvSeriesRepository.findByTitle(tvSeriesTitle)
                .map(tvSeriesMapper::tvSeriesEntityToTvSeriesDto)
                .orElseThrow(() -> new RuntimeException("Tv Series not found: " + tvSeriesTitle));
    }
}
