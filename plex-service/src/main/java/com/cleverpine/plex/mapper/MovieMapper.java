package com.cleverpine.plex.mapper;

import com.cleverpine.plex.dto.MovieDto;
import com.cleverpine.plex.dto.ReceivedMovieDto;
import com.cleverpine.plex.entity.future.MovieEntity;
import com.cleverpine.plex.model.MovieListItem;
import com.cleverpine.plex.model.MoviesListResponse;
import com.cleverpine.plex.model.SingleMovie;
import com.cleverpine.plex.model.SingleMovieResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieDto movieEntityToMovieDto(MovieEntity entity);
    List<MovieDto> movieEntityListToMovieDtoList(List<MovieEntity> entityList);

    MovieListItem movieDtoToMovieListItem(MovieDto dto);
    List<MovieListItem> movieDtoListToMovieListItemList(List<MovieDto> dtoList);

    default MoviesListResponse movieDtoListToMoviesListResponse(List<MovieDto> dtoList) {
        MoviesListResponse response = new MoviesListResponse();
        List<MovieListItem> items = movieDtoListToMovieListItemList(dtoList);
        response.setData(items);
        return response;
    }

    SingleMovie movieDtoToSingleMovie(MovieDto movieDto);

    default SingleMovieResponse movieDtoToSingleMovieResponse(MovieDto movieDto) {
        SingleMovieResponse response = new SingleMovieResponse();
        SingleMovie movie = movieDtoToSingleMovie(movieDto);
        response.setData(movie);
        return response;
    }

    List<MovieEntity> receivedMovieDtoListToMovieEntityList(List<ReceivedMovieDto> list);

}
