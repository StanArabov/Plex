package com.cleverpine.plex.service;

import com.cleverpine.plex.dto.MovieDto;
import com.cleverpine.plex.entity.future.MovieEntity;
import com.cleverpine.plex.projection.MovieProjection;
import com.cleverpine.plex.repository.future.MoviesRepository;
import com.cleverpine.plex.repository.legacy.MetadataItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    final private MetadataItemsRepository metadataItemsRepository;
    final private MoviesRepository moviesRepository;

    public void simpleMovieETL() {
        List<MovieEntity> moviesList = new ArrayList<>();
        MovieProjection[] movies = metadataItemsRepository.findMetadataAndMedia().toArray(new MovieProjection[0]);

        for(MovieProjection movieProjection: movies) {
            MovieEntity movie = new MovieEntity(
                    movieProjection.getTitle(),
                    movieProjection.getDescription(),
                    movieProjection.getRating(),
                    movieProjection.getReleaseDate(),
                    movieProjection.getDuration(),
                    movieProjection.getYear(),
                    movieProjection.getDirector(),
                    movieProjection.getWriter(),
                    movieProjection.getGenres(),
                    movieProjection.getStars(),
                    movieProjection.getAudio(),
                    movieProjection.getSubtitles()
            );
            moviesList.add(movie);
        }
        moviesRepository.saveAll(moviesList);
    }

    public List<MovieDto> getMovieList(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<MovieEntity> movePage = moviesRepository.findAll(pageRequest);
        return movePage.getContent().stream()
                .map(movie -> new MovieDto(movie.getMovie_id(), movie.getTitle(), movie.getDescription(), movie.getRating(), movie.getReleaseDate(), movie.getDuration(), movie.getYear(), movie.getDirector(), movie.getWriter(), movie.getGenres(), movie.getStars(), movie.getAudio(), movie.getSubtitles()))
                .collect(Collectors.toList());
    }
}
