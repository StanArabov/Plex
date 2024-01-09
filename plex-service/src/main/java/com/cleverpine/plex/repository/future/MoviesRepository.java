package com.cleverpine.plex.repository.future;

import com.cleverpine.plex.entity.future.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoviesRepository extends JpaRepository<MovieEntity, Long> {
    Optional<MovieEntity> findByTitle(String title);
}
