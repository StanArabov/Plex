package com.cleverpine.plex.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class MovieDto {

    private Long id;
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
    private String audio;
    private String subtitles;

}
