package com.cleverpine.plex.controller;

import com.cleverpine.plex.api.TvSeriesApi;
import com.cleverpine.plex.auth.ViravaSecured;
import com.cleverpine.plex.auth.roles.Resources;
import com.cleverpine.plex.dto.EpisodeDto;
import com.cleverpine.plex.dto.TvSeriesDto;
import com.cleverpine.plex.mapper.EpisodeMapper;
import com.cleverpine.plex.mapper.TvSeriesMapper;
import com.cleverpine.plex.model.*;
import com.cleverpine.plex.service.interfaces.EpisodeService;
import com.cleverpine.plex.service.interfaces.TvSeriesService;
import com.cleverpine.viravaspringhelper.dto.ScopeType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TvSeriesController implements TvSeriesApi {
    private final TvSeriesService tvSeriesService;
    private final EpisodeService episodeService;

    @Override
    @ViravaSecured(resource = Resources.TV_SERIES, scope = ScopeType.READ)
    public ResponseEntity<TvSeriesListResponse> getPaginatedTvSeriesList(Integer page, Integer size) {
        List<TvSeriesDto> tvSeriesDtos = tvSeriesService.getTvSeriesList(page, size);
        TvSeriesListResponse response = TvSeriesMapper.INSTANCE.tvSeriesDtoListToTvSeriesListResponse(tvSeriesDtos);
        return ResponseEntity.ok(response);
    }

    @Override
    @ViravaSecured(resource = Resources.TV_SERIES, scope = ScopeType.READ)
    public ResponseEntity<SingleTvSeriesResponse> getTvSeriesById(Integer tvSeriesId) {
        TvSeriesDto tvSeriesDto = tvSeriesService.getTvSeriesById(tvSeriesId);
        SingleTvSeriesResponse response = TvSeriesMapper.INSTANCE.tvSeriesDtoToSingleTvSeriesResponse(tvSeriesDto);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<EpisodeListResponse> getSeasonEpisodes(Integer seasonId) {
        List<EpisodeDto> episodeDtoList = episodeService.getSeasonEpisodes(seasonId);
        EpisodeListResponse response = EpisodeMapper.INSTANCE.episodeDtosToEpisodeListResponse(episodeDtoList);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<SingleTvSeriesResponse> getTvSeriesByTitle(String title) {
        TvSeriesDto tvSeriesDto = tvSeriesService.getTvSeriesByTitle(title);
        SingleTvSeriesResponse response = TvSeriesMapper.INSTANCE.tvSeriesDtoToSingleTvSeriesResponse(tvSeriesDto);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<SingleEpisodeResponse> getEpisodeById(Integer episodeId) {
        EpisodeDto episodeDto = episodeService.getEpisodeById(episodeId);
        SingleEpisodeResponse response = EpisodeMapper.INSTANCE.episodeDtoToSingleEpisodeResponse(episodeDto);
        return ResponseEntity.ok(response);
    }
}
