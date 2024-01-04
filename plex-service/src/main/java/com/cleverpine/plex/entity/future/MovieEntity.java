package com.cleverpine.plex.entity.future;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "movies", indexes = @Index(name = "movie_title_index", columnList = "title", unique = false))
public class MovieEntity {

    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name="description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(name = "rating")
    private Double rating;

    @Column(name="release_date")
    private LocalDate releaseDate;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "year")
    private Integer year;

    @Column(name = "director", columnDefinition = "NVARCHAR(256)")
    private String director;

    @Column(name = "writer", columnDefinition = "NVARCHAR(MAX)")
    private String writer;

    @Column(name = "genres", columnDefinition = "NVARCHAR(MAX)")
    private String genres;

    @Column(name = "stars", columnDefinition = "NVARCHAR(MAX)")
    private String stars;

    @Column(name = "audio", columnDefinition = "NVARCHAR(256)")
    private String audio;

    @Column(name = "subtitles", columnDefinition = "NVARCHAR(256)")
    private String subtitles;

    protected MovieEntity(){};

    public MovieEntity(String title, String description, Double rating, LocalDate releaseDate, Integer duration, Integer year, String director, String writer, String genres, String stars, String audio, String subtitles) {
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
