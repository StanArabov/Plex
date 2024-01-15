package com.cleverpine.plex.service.implementations;

import com.cleverpine.plex.dto.ReceivedMovieDto;
import com.cleverpine.plex.entity.future.MovieEntity;
import com.cleverpine.plex.mapper.MovieMapper;
import com.cleverpine.plex.repository.future.MoviesRepository;
import com.cleverpine.plex.service.interfaces.MovieWriterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
@AllArgsConstructor
public class MovieWriterServiceImpl implements MovieWriterService {

    private final ExecutorService executorService;
    private final MovieMapper movieMapper;
    private final MoviesRepository moviesRepository;

    @Override
    public CompletableFuture<List<MovieEntity>> transformMoviesAsync(List<ReceivedMovieDto> receivedData) {
        return CompletableFuture.supplyAsync(() ->
                movieMapper.receivedMovieDtoListToMovieEntityList(receivedData), executorService);
    }

    @Override
    public CompletableFuture<Void> saveMoviesAsync(List<MovieEntity> entities) {
        return CompletableFuture.runAsync(() -> {
            moviesRepository.saveAll(entities);
        }, executorService);
    }
}
