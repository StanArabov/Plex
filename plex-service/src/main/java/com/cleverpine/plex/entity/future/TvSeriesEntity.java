package com.cleverpine.plex.entity.future;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tv_series")
public class TvSeriesEntity {
    @Id
    @Column(name = "tv_series_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "series_id")
    private Set<SeasonEntity> seasons;

    protected TvSeriesEntity() {}
    public TvSeriesEntity(String title, String description, Double rating, LocalDate releaseDate, Integer duration, Integer year, String director, String writer, String genres, String stars) {
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
    }
}
