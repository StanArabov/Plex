package com.cleverpine.plex.projection;

import java.time.LocalDateTime;

public interface MovieProjection {

    Integer getMetadataId();
    String getTitle();

    String getDescription();

    Double getRating();

    LocalDateTime getReleaseDate();

    Integer getDuration();

    Integer getYear();

    Integer getType();

    Integer getIndex();

    Integer getParentId();

    String getDirector();

    String getWriter();

    String  getStars();

    String getGenres();

    String  getAudio();

    String getSubtitles();
}
