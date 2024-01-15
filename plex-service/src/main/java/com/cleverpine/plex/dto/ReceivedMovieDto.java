package com.cleverpine.plex.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReceivedMovieDto {
    private String title;
    private String description;
    private Double rating;
    private LocalDateTime releaseDate;
    private Integer duration;
    private Integer year;
    private String director;
    private String writer;
    private String genres;
    private String stars;
    private String audio;
    private String subtitles;
}
