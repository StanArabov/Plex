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

@Mapper(componentModel = "spring")
public interface EpisodeMapper {

    EpisodeDto episodeEntityToEpisodeDto(EpisodeEntity episodeEntity);

    List<EpisodeDto> episodeEntityListToEpisodeDtoList(List<EpisodeEntity> entities);

    @Mapping(source = "number", target = "episodeNumber")
    EpisodeListItem episodeDtoToEpisodeListItem(EpisodeDto dto);

    List<EpisodeListItem> episodeDtoListToEpisodeItemsList(List<EpisodeDto> dtos);

    default EpisodeListResponse episodeDtosToEpisodeListResponse(List<EpisodeDto> dtos) {
        EpisodeListResponse response = new EpisodeListResponse();
        List<EpisodeListItem> episodeListItems = episodeDtoListToEpisodeItemsList(dtos);
        response.setData(episodeListItems);
        return response;
    }

    SingleEpisode episodeDtoToSingleEpisode(EpisodeDto episodeDto);

    default SingleEpisodeResponse episodeDtoToSingleEpisodeResponse(EpisodeDto episodeDto) {
        SingleEpisodeResponse response = new SingleEpisodeResponse();
        response.setData(episodeDtoToSingleEpisode(episodeDto));
        return  response;
    }

}
