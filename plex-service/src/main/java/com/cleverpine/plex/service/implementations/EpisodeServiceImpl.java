package com.cleverpine.plex.service.implementations;

import com.cleverpine.plex.dto.EpisodeDto;
import com.cleverpine.plex.entity.future.EpisodeEntity;
import com.cleverpine.plex.mapper.EpisodeMapper;
import com.cleverpine.plex.repository.future.EpisodesRepository;
import com.cleverpine.plex.service.interfaces.EpisodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EpisodeServiceImpl implements EpisodeService {
    private final EpisodesRepository episodesRepository;
    @Override
    public List<EpisodeDto> getSeasonEpisodes(Integer seasonId) {
        List<EpisodeEntity> episodeEntities = episodesRepository.findBySeasonId(seasonId);
        return EpisodeMapper.INSTANCE.episodeEntityListToEpisodeDtoList(episodeEntities);
    }

    @Override
    public EpisodeDto getEpisodeById(Integer episodeId) {
        try {
            Optional<EpisodeEntity> episode = episodesRepository.findById(episodeId);
            if (episode.isEmpty()) {
                throw new RuntimeException("Episode not found");
            }
            return EpisodeMapper.INSTANCE.episodeEntityToEpisodeDto(episode.get());
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Episode not found", e);
        }

    }
}
