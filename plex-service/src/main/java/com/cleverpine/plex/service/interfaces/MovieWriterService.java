package com.cleverpine.plex.service.interfaces;

import com.cleverpine.plex.dto.ReceivedMovieDto;
import com.cleverpine.plex.entity.future.MovieEntity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MovieWriterService {
    CompletableFuture<List<MovieEntity>> transformMoviesAsync(List<ReceivedMovieDto> receivedData);

    CompletableFuture<Void> saveMoviesAsync(List<MovieEntity> entities);
}
