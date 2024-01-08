package com.cleverpine.plex.repository.future;

import com.cleverpine.plex.entity.future.TvSeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TvSeriesRepository extends JpaRepository<TvSeriesEntity, Integer> {
    Optional<TvSeriesEntity> findByTitle(String title);
}
