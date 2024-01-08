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
import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TvSeriesServiceImpl implements TvSeriesService {
    private final TvSeriesRepository tvSeriesRepository;
    @Override
    public List<TvSeriesDto> getTvSeriesList(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<TvSeriesEntity> tvSeriesPage = tvSeriesRepository.findAll(pageRequest);
        return TvSeriesMapper.INSTANCE.tvSeriesEntityListToTvSeriesDtoList(tvSeriesPage.getContent());
    }

    @Override
    public TvSeriesDto getTvSeriesById(Integer tvSeriesId) {
        try {
            Optional<TvSeriesEntity> entity = tvSeriesRepository.findById(tvSeriesId);
            if (entity.isEmpty()) {
                throw new RuntimeException("Tv Series not found");
            }
            return TvSeriesMapper.INSTANCE.tvSeriesEntityToTvSeriesDto(entity.get());
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Tv Series not found", e);
        }
    }

    @Override
    public TvSeriesDto getTvSeriesByTitle(String tvSeriesTitle) {
        try {
            Optional<TvSeriesEntity> entity = tvSeriesRepository.findByTitle(tvSeriesTitle);
            if (entity.isEmpty()) {
                throw new RuntimeException("Tv Series not found");
            }
            return TvSeriesMapper.INSTANCE.tvSeriesEntityToTvSeriesDto(entity.get());
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Tv Series not found", e);
        }
    }
}
