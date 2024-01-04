package com.cleverpine.plex.service.interfaces;

import com.cleverpine.plex.dto.MovieDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MovieServiceInterface {
    public List<MovieDto> getMovieList(Integer page, Integer size);
    public MovieDto getMovieById(Long movieId);
    public MovieDto getMovieByTitle(String title);
    public void simpleMovieETL();
}
