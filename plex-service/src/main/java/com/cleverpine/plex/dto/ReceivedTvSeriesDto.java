package com.cleverpine.plex.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReceivedTvSeriesDto {
    private Integer metadataId;
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
    private List<ReceivedSeasonDto> seasons;
}
