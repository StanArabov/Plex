package com.cleverpine.plex.service.interfaces;

import com.cleverpine.plex.dto.MovieDto;

import java.util.List;


public interface MovieService {
    List<MovieDto> getMovieList(Integer page, Integer size);
    MovieDto getMovieById(Long movieId);
    MovieDto getMovieByTitle(String title);
    void simpleMovieETL();
}
