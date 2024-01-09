package com.cleverpine.plex.service.interfaces;

import com.cleverpine.plex.dto.TvSeriesDto;

import java.util.List;

public interface
TvSeriesService {
    List<TvSeriesDto> getTvSeriesList(Integer page, Integer size);

    TvSeriesDto getTvSeriesById(Integer tvSeriesId);

    TvSeriesDto getTvSeriesByTitle(String tvSeriesTitle);

}
