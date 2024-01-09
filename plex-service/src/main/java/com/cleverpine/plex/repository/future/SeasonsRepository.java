package com.cleverpine.plex.repository.future;

import com.cleverpine.plex.entity.future.SeasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SeasonsRepository extends JpaRepository<SeasonEntity, UUID> {
}
