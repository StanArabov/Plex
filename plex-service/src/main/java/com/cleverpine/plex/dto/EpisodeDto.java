package com.cleverpine.plex.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class EpisodeDto {

    private Integer id;
    private String title;
    private String description;
    private Double rating;
    private LocalDate releaseDate;
    private String director;
    private String writer;
    private String stars;
    private Integer duration;
    private Integer year;
    private Integer number;
    private String audio;
    private String subtitles;

}
