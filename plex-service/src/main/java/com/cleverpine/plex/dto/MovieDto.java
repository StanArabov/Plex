package com.cleverpine.plex.dto;

import jakarta.persistence.Column;
import lombok.Getter;

import java.time.LocalDate;

@Getter
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

    public MovieDto(Long id, String title, String description, Double rating, LocalDate releaseDate, Integer duration, Integer year, String director, String writer, String genres, String stars, String audio, String subtitles) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.year = year;
        this.director = director;
        this.writer = writer;
        this.genres = genres;
        this.stars = stars;
        this.audio = audio;
        this.subtitles = subtitles;
    }

}
