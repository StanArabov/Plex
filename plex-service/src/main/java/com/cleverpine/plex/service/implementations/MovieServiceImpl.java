package com.cleverpine.plex.service.implementations;

import com.cleverpine.plex.dto.MovieDto;
import com.cleverpine.plex.entity.future.EpisodeEntity;
import com.cleverpine.plex.entity.future.MovieEntity;
import com.cleverpine.plex.entity.future.SeasonEntity;
import com.cleverpine.plex.entity.future.TvSeriesEntity;
import com.cleverpine.plex.mapper.MovieMapper;
import com.cleverpine.plex.projection.MovieProjection;
import com.cleverpine.plex.projection.SeasonsProjection;
import com.cleverpine.plex.repository.future.EpisodesRepository;
import com.cleverpine.plex.repository.future.MoviesRepository;
import com.cleverpine.plex.repository.future.SeasonsRepository;
import com.cleverpine.plex.repository.future.TvSeriesRepository;
import com.cleverpine.plex.repository.legacy.MetadataItemsRepository;
import com.cleverpine.plex.service.interfaces.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
// TODO: It will be refactored with proper mapping and separate service for reading/writing data
public class MovieServiceImpl implements MovieService {

    private final MetadataItemsRepository metadataItemsRepository;
    private final TvSeriesRepository tvSeriesRepository;
    private final EpisodesRepository episodesRepository;
    private final SeasonsRepository seasonsRepository;
    private final MoviesRepository moviesRepository;

    public void simpleMovieETL() {
        List<MovieEntity> moviesList = new ArrayList<>();
        List<TvSeriesEntity> tvSeriesList = new ArrayList<>();
        List<SeasonEntity> seasons = new ArrayList<>();
        MovieProjection[] movies = metadataItemsRepository.findMetadataAndMedia().toArray(new MovieProjection[0]);

//        MovieProjection[] first10Movies = Arrays.copyOfRange(movies, 0, 10);
        for(MovieProjection movieProjection: movies) {
            if (movieProjection.getType() == 1) {
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
            if (movieProjection.getType() == 2) {
                Set<SeasonsProjection> legacySeasons = metadataItemsRepository.getSeasons(movieProjection.getMetadataId());

                Integer[] seasonIds = legacySeasons.stream()
                        .map(SeasonsProjection::getMetadataId)
                        .toArray(Integer[]::new);
//                System.out.println("======================");
//                System.out.println(Arrays.toString(seasonIds));
//                System.out.println("======================");
                List<MovieProjection> legacySeasonEpisodes = metadataItemsRepository.findSeasonEpisodes(seasonIds);
//                System.out.println("======================");
//                System.out.println(legacySeasonEpisodes.size());
//                System.out.println("======================");
//                System.out.println("----------------------------");
//                System.out.println(seasonEpisodes.size());
//                System.out.println("----------------------------");

                Set<SeasonEntity> tvSeasons = legacySeasons.stream()
                        .map(season ->  {
                            List<MovieProjection> legacyEpisodes = legacySeasonEpisodes.stream().filter(e -> Objects.equals(e.getParentId(), season.getMetadataId())).toList();
                            List<EpisodeEntity> seasonEpisodes = legacyEpisodes.stream().map(e ->  new EpisodeEntity(
                                            e.getTitle(),
                                            e.getDescription(),
                                            e.getRating(),
                                            (e.getReleaseDate() != null)
                                                    ? e.getReleaseDate().toLocalDate()
                                                    : null,
                                            e.getDirector(),
                                            e.getWriter(),
                                            e.getStars(),
                                            e.getDuration(),
                                            e.getYear(),
                                            e.getIndex(),
                                            e.getAudio(),
                                            e.getSubtitles()
                                    )

                            ).toList();
                            SeasonEntity newSeason =  new SeasonEntity(season.getNumber());
                            newSeason.setEpisodes(seasonEpisodes);
//                            episodesRepository.saveAll(seasonEpisodes);
                            return newSeason;
                        })
                        .collect(Collectors.toSet());


                TvSeriesEntity series = new TvSeriesEntity(
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
                        movieProjection.getStars()
                );

                series.setSeasons(tvSeasons);
                tvSeriesList.add(series);
            }

        }
        moviesRepository.saveAll(moviesList);
        tvSeriesRepository.saveAll(tvSeriesList);
//        seasonsRepository.saveAll(seasons);
    }

    @Override
    public List<MovieDto> getMovieList(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<MovieEntity> movePage = moviesRepository.findAll(pageRequest);
        return MovieMapper.INSTANCE.movieEntityListToMovieDtoList(movePage.getContent());
    }

    @Override
    public MovieDto getMovieById(Long movieId) {
        return moviesRepository.findById(movieId)
                .map(MovieMapper.INSTANCE::movieEntityToMovieDto)
                .orElseThrow(() ->  new RuntimeException("Movie not found: " + movieId));
    }

    @Override
    public MovieDto getMovieByTitle(String title) {
        return moviesRepository.findByTitle(title)
                .map(MovieMapper.INSTANCE::movieEntityToMovieDto)
                .orElseThrow(() ->  new RuntimeException("Movie not found: " + title));
    }
}
