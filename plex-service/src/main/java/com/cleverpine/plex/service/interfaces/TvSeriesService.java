package com.cleverpine.plex.service.interfaces;

import com.cleverpine.plex.dto.TvSeriesDto;

import java.util.List;

public interface
TvSeriesService {
    public List<TvSeriesDto> getTvSeriesList(Integer page, Integer size);

    public TvSeriesDto getTvSeriesById(Integer tvSeriesId);

    public TvSeriesDto getTvSeriesByTitle(String tvSeriesTitle);

}
