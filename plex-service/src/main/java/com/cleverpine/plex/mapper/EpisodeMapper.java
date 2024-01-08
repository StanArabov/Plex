package com.cleverpine.plex.mapper;

import com.cleverpine.plex.dto.EpisodeDto;
import com.cleverpine.plex.entity.future.EpisodeEntity;
import com.cleverpine.plex.model.EpisodeListItem;
import com.cleverpine.plex.model.EpisodeListResponse;
import com.cleverpine.plex.model.SingleEpisode;
import com.cleverpine.plex.model.SingleEpisodeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EpisodeMapper {
    EpisodeMapper INSTANCE = Mappers.getMapper(EpisodeMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "rating", target = "rating")
    @Mapping(source = "releaseDate", target = "releaseDate")
    @Mapping(source = "director", target = "director")
    @Mapping(source = "writer", target = "writer")
    @Mapping(source = "stars", target = "stars")
    @Mapping(source = "duration", target = "duration")
    @Mapping(source = "year", target = "year")
    @Mapping(source = "number", target = "number")
    @Mapping(source = "audio", target = "audio")
    @Mapping(source = "subtitles", target = "subtitles")
    EpisodeDto episodeEntityToEpisodeDto(EpisodeEntity episodeEntity);

    List<EpisodeDto> episodeEntityListToEpisodeDtoList(List<EpisodeEntity> entities);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "number", target = "episodeNumber")
    EpisodeListItem episodeDtoToEpisodeListItem(EpisodeDto dto);

    List<EpisodeListItem> episodeDtoListToEpisodeItemsList(List<EpisodeDto> dtos);

    default EpisodeListResponse episodeDtosToEpisodeListResponse(List<EpisodeDto> dtos) {
        EpisodeListResponse response = new EpisodeListResponse();
        List<EpisodeListItem> episodeListItems = INSTANCE.episodeDtoListToEpisodeItemsList(dtos);
        response.setData(episodeListItems);
        return response;
    }

//    private String description;
//    private Double rating;
//    private LocalDate releaseDate;
//    private String director;
//    private String writer;
//    private String stars;
//    private Integer duration;
//    private Integer year;
//    private Integer number;
//    private String audio;
//    private String subtitles;
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "rating", target = "rating")
    @Mapping(source = "releaseDate", target = "releaseDate")
    @Mapping(source = "director", target = "director")
    @Mapping(source = "writer", target = "writer")
    @Mapping(source = "stars", target = "stars")
    @Mapping(source = "duration", target = "duration")
    @Mapping(source = "year", target = "year")
    @Mapping(source = "number", target = "number")
    @Mapping(source = "audio", target = "audio")
    @Mapping(source = "subtitles", target = "subtitles")
    SingleEpisode episodeDtoToSingleEpisode(EpisodeDto episodeDto);

    default SingleEpisodeResponse episodeDtoToSingleEpisodeResponse(EpisodeDto episodeDto) {
        SingleEpisodeResponse response = new SingleEpisodeResponse();
        response.setData(INSTANCE.episodeDtoToSingleEpisode(episodeDto));
        return  response;
    }

}
