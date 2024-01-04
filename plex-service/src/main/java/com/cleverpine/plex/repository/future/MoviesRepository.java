package com.cleverpine.plex.repository.future;

import com.cleverpine.plex.entity.future.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MoviesRepository extends JpaRepository<MovieEntity, Long> {

    Optional<MovieEntity> findByTitle(String title);
}
