package com.cleverpine.plex.service.implementations;

import com.cleverpine.plex.dto.MovieDto;
import com.cleverpine.plex.entity.future.MovieEntity;
import com.cleverpine.plex.mapper.MovieMapper;
import com.cleverpine.plex.projection.MovieProjection;
import com.cleverpine.plex.repository.future.MoviesRepository;
import com.cleverpine.plex.repository.legacy.MetadataItemsRepository;
import com.cleverpine.plex.service.interfaces.MovieServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieServiceInterface {

    final private MetadataItemsRepository metadataItemsRepository;
    final private MoviesRepository moviesRepository;
    final private MovieMapper movieMapper;

    public void simpleMovieETL() {
        List<MovieEntity> moviesList = new ArrayList<>();
        MovieProjection[] movies = metadataItemsRepository.findMetadataAndMedia().toArray(new MovieProjection[0]);

        for(MovieProjection movieProjection: movies) {
            MovieEntity movie = new MovieEntity(
                    movieProjection.getTitle(),
                    movieProjection.getDescription(),
                    movieProjection.getRating(),
                    (movieProjection.getReleaseDate() != null)
                            ? movieProjection.getReleaseDate().toLocalDate()
                            : null,
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

    @Override
    public List<MovieDto> getMovieList(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<MovieEntity> movePage = moviesRepository.findAll(pageRequest);
        return movePage.getContent().stream()
                .map(movie -> new MovieDto(movie.getId(), movie.getTitle(), movie.getDescription(), movie.getRating(), movie.getReleaseDate(), movie.getDuration(), movie.getYear(), movie.getDirector(), movie.getWriter(), movie.getGenres(), movie.getStars(), movie.getAudio(), movie.getSubtitles()))
                .collect(Collectors.toList());
    }

    @Override
    public MovieDto getMovieById(Long movieId) {
        try {
            Optional<MovieEntity> movie = moviesRepository.findById(movieId);
            if (movie.isEmpty()) {
                throw new RuntimeException("Movie not found");
            }
            return movieMapper.movieEntityToMovieDto(movie.get());
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Movie not found", e);
        }
    }

    @Override
    public MovieDto getMovieByTitle(String title) {
        try {
            Optional<MovieEntity> movie = moviesRepository.findByTitle(title);
            if (movie.isEmpty()) {
                throw new RuntimeException("Movie not found");
            }
            return movieMapper.movieEntityToMovieDto(movie.get());
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Movie not found", e);
        }
    }
}
