package com.cleverpine.plex.dto;

import com.cleverpine.plex.entity.future.SeasonEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
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

    public EpisodeDto(Integer id, String title, String description, Double rating, LocalDate releaseDate, String director, String writer, String stars, Integer duration, Integer year, Integer number, String audio, String subtitles) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.director = director;
        this.writer = writer;
        this.stars = stars;
        this.duration = duration;
        this.year = year;
        this.number = number;
        this.audio = audio;
        this.subtitles = subtitles;
    }
}
