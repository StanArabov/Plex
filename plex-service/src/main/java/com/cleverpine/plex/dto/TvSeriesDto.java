package com.cleverpine.plex.dto;

import com.cleverpine.plex.entity.future.SeasonEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
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

    public TvSeriesDto(Integer id, String title, String description, Double rating, LocalDate releaseDate, Integer duration, Integer year, String director, String writer, String genres, String stars, Set<SeasonEntity> seasons) {
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
        this.seasons = seasons;
    }
}
