package com.cleverpine.plex.repository.future;

import com.cleverpine.plex.entity.future.EpisodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpisodesRepository extends JpaRepository<EpisodeEntity, Integer> {
    List<EpisodeEntity> findBySeasonId (Integer seasonId);
}
