package com.cleverpine.plex.repository.future;

import com.cleverpine.plex.entity.future.EpisodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodesRepository extends JpaRepository<EpisodeEntity, Integer> {
    List<EpisodeEntity> findBySeasonId (Integer seasonId);
}
