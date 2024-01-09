package com.cleverpine.plex.dto;

import com.cleverpine.plex.entity.future.SeasonEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class TvSeriesDto {

    private Integer id;
    private String title;
    private String description;
    private Double rating;
    private LocalDate releaseDate;
    private Integer duration;
    private Integer year;
    private String director;
    private String writer;
    private String genres;
    private String stars;
    private Set<SeasonEntity> seasons;

}
