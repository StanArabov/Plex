package com.cleverpine.plex.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReceivedSeasonDto {
    Integer metadataId;
    Integer number;
    List<ReceivedEpisodeDto> episodes;
}
