package com.cleverpine.plex.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReceivedEpisodeDto {
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
