package com.cleverpine.plex.service.interfaces;

import com.cleverpine.plex.dto.EpisodeDto;

import java.util.List;

public interface EpisodeService {
    public List<EpisodeDto> getSeasonEpisodes(Integer seasonId);

    public EpisodeDto getEpisodeById(Integer episodeId);
}
