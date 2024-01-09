package com.cleverpine.plex.service.interfaces;

import com.cleverpine.plex.dto.EpisodeDto;

import java.util.List;

public interface EpisodeService {
    List<EpisodeDto> getSeasonEpisodes(Integer seasonId);

    EpisodeDto getEpisodeById(Integer episodeId);
}
