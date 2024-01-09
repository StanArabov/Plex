package com.cleverpine.plex.controller;

import com.cleverpine.plex.api.MoviesApi;
import com.cleverpine.plex.auth.ViravaSecured;
import com.cleverpine.plex.auth.roles.Resources;
import com.cleverpine.plex.dto.MovieDto;
import com.cleverpine.plex.mapper.MovieMapper;
import com.cleverpine.plex.model.MoviesListResponse;
import com.cleverpine.plex.model.SingleMovieResponse;
import com.cleverpine.plex.service.interfaces.MovieService;
import com.cleverpine.viravaspringhelper.dto.ScopeType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController implements MoviesApi {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @Override
    @ViravaSecured(resource = Resources.MOVIES, scope = ScopeType.READ)
    public ResponseEntity<String> apiMoviesTestGet() {
        movieService.simpleMovieETL();
        return ResponseEntity.ok("Movie Test");
    }

    @Override
    @ViravaSecured(resource = Resources.MOVIES, scope = ScopeType.READ)
    public ResponseEntity<MoviesListResponse> getPaginatedMovieList(Integer page, Integer size) {
        List<MovieDto> movieList = movieService.getMovieList(page, size);
        MoviesListResponse response = movieMapper.movieDtoListToMoviesListResponse(movieList);
        return ResponseEntity.ok(response);
    }

    @Override
    @ViravaSecured(resource = Resources.MOVIES, scope = ScopeType.READ)
    public ResponseEntity<SingleMovieResponse> getMovieById(Long movieId) {
        MovieDto searchedMovie = movieService.getMovieById(movieId);
        SingleMovieResponse response = movieMapper.movieDtoToSingleMovieResponse(searchedMovie);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<SingleMovieResponse> getMovieByTitle(String title) {
        MovieDto searchedMovie = movieService.getMovieByTitle(title);
        SingleMovieResponse response = movieMapper.movieDtoToSingleMovieResponse(searchedMovie);
        return ResponseEntity.ok(response);
    }
}
