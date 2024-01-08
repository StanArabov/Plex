package com.cleverpine.plex.repository.future;

import com.cleverpine.plex.entity.future.SeasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SeasonsRepository extends JpaRepository<SeasonEntity, UUID> {
}
