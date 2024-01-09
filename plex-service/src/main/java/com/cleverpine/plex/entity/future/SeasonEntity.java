package com.cleverpine.plex.entity.future;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "seasons")
public class SeasonEntity {
    @Id
    @Column(name = "season_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name  = "number")
    private Integer number;

    @Column(name = "series_id")
    private Integer seriesId;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "season_id")
    private List<EpisodeEntity> episodes;

    protected SeasonEntity() {}

    public SeasonEntity(Integer number) {
        this.number = number;
    }
}
