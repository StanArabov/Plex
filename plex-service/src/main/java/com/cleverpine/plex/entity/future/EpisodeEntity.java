package com.cleverpine.plex.entity.future;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "episodes")
public class EpisodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "episode_id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name="description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(name = "rating")
    private Double rating;

    @Column(name="release_date")
    private LocalDate releaseDate;

    @Column(name = "director", columnDefinition = "NVARCHAR(256)")
    private String director;

    @Column(name = "writer", columnDefinition = "NVARCHAR(MAX)")
    private String writer;

    @Column(name = "stars", columnDefinition = "NVARCHAR(MAX)")
    private String stars;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "year")
    private Integer year;

    @Column(name = "number")
    private Integer number;

    @Column(name = "audio", columnDefinition = "NVARCHAR(256)")
    private String audio;

    @Column(name = "subtitles", columnDefinition = "NVARCHAR(256)")
    private String subtitles;

    @Column(name = "season_id")
    private Integer seasonId;

    protected EpisodeEntity() {}

    public EpisodeEntity(String title, String description, Double rating, LocalDate releaseDate, String director, String writer, String stars, Integer duration, Integer year, Integer number, String audio, String subtitles) {
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
