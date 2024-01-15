package com.cleverpine.plex.service.implementations;

import com.cleverpine.plex.dto.ReceivedMovieDto;
import com.cleverpine.plex.dto.ReceivedTvSeriesDto;
import com.cleverpine.plex.service.interfaces.MovieWriterService;
import com.cleverpine.plex.service.interfaces.ReceiverService;
import com.cleverpine.plex.service.interfaces.TvSeriesWriterService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class ReceiverServiceImpl implements ReceiverService {

    private final ObjectMapper objectMapper;
    private final MovieWriterService movieWriterService;
    private final TvSeriesWriterService tvSeriesWriterService;

    @Override
    @RabbitListener(queues = "plex-movies-queue")
    public void receiveMovies(String message) {
        receiverHandler(message, this::writeMovies, ReceivedMovieDto.class)
                .whenComplete((result, exception) -> {
                    if (exception != null) {
                        System.out.println("Error processing movies: " + exception.getMessage());
                    }
                });
    }

    @Override
    @RabbitListener(queues = "plex-tv-series-queue")
    public void receiveTvSeries(String message) {
        receiverHandler(message, this::writeTvSeries, ReceivedTvSeriesDto.class)
                .whenComplete((result, exception) -> {
                    if (exception != null) {
                        System.out.println("Error processing movies: " + exception.getMessage());
                    }
                });
    }

    private <T> CompletableFuture<Void> receiverHandler(String message, Function<List<T>, CompletableFuture<Void>> write, Class<T> elementType) {
        try {
            JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, elementType);
            List<T> receivedDtoList = objectMapper.readValue(message, type);

            System.out.println(" [x] Received '" + receivedDtoList.size() + "' ------");
            return write.apply(receivedDtoList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return CompletableFuture.completedFuture(null);
        }
    }

    private CompletableFuture<Void> writeMovies(List<ReceivedMovieDto> receivedMovieDtoList) {
        return movieWriterService.transformMoviesAsync(receivedMovieDtoList).thenComposeAsync(movieWriterService::saveMoviesAsync);
    }

    private CompletableFuture<Void> writeTvSeries(List<ReceivedTvSeriesDto> receivedTvSeriesDtoList) {
        return tvSeriesWriterService.transformTvSeriesAsync(receivedTvSeriesDtoList).thenComposeAsync(tvSeriesWriterService::saveTvSeriesAsync);
    }

}
