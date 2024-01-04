package com.cleverpine.plex.mapper;

import com.cleverpine.plex.dto.MovieDto;
import com.cleverpine.plex.entity.future.MovieEntity;
import com.cleverpine.plex.model.MovieListItem;
import com.cleverpine.plex.model.SingleMovie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieMapper {
    public List<MovieListItem> movieDtoToMovieListItem(List<MovieDto> movieDtos) {
        return movieDtos.stream().map(item -> {
            MovieListItem newItem = new MovieListItem();
            newItem.setId(item.getId());
            newItem.setTitle(item.getTitle());
            return newItem;
        }).toList();
    }

    public SingleMovie movieDtoToSingleMovie(MovieDto movieDto) {
        SingleMovie singleMovie = new SingleMovie();
        singleMovie.setTitle(movieDto.getTitle());
        singleMovie.setDescription(movieDto.getDescription());
        singleMovie.setRating(movieDto.getRating());
        singleMovie.setReleaseDate(movieDto.getReleaseDate());
        singleMovie.setDuration(movieDto.getDuration());
        singleMovie.setYear(movieDto.getYear());
        singleMovie.setDirector(movieDto.getDirector());
        singleMovie.setWriter(movieDto.getWriter());
        singleMovie.setGenres(movieDto.getGenres());
        singleMovie.setStars(movieDto.getStars());
        singleMovie.setAudio(movieDto.getAudio());
        singleMovie.setSubtitles(movieDto.getSubtitles());
        return singleMovie;
    }

    public MovieDto movieEntityToMovieDto(MovieEntity movieEntity) {
        return new MovieDto(
                movieEntity.getId(),
                movieEntity.getTitle(),
                movieEntity.getDescription(),
                movieEntity.getRating(),
                movieEntity.getReleaseDate(),
                movieEntity.getDuration(),
                movieEntity.getYear(),
                movieEntity.getDirector(),
                movieEntity.getWriter(),
                movieEntity.getGenres(),
                movieEntity.getStars(),
                movieEntity.getAudio(),
                movieEntity.getSubtitles()
        );
    }
}
