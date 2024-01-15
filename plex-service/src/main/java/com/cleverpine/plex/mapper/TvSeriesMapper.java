package com.cleverpine.plex.mapper;

import com.cleverpine.plex.dto.ReceivedEpisodeDto;
import com.cleverpine.plex.dto.ReceivedSeasonDto;
import com.cleverpine.plex.dto.ReceivedTvSeriesDto;
import com.cleverpine.plex.dto.TvSeriesDto;
import com.cleverpine.plex.entity.future.EpisodeEntity;
import com.cleverpine.plex.entity.future.SeasonEntity;
import com.cleverpine.plex.entity.future.TvSeriesEntity;
import com.cleverpine.plex.model.*;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface TvSeriesMapper {
    TvSeriesListItem tvSeriesDtoToTvSeriesListItem(TvSeriesDto dto);

    List<TvSeriesListItem> tvSeriesDtoListToTvSeriesListItemList(List<TvSeriesDto> dtoList);

    default TvSeriesListResponse tvSeriesDtoListToTvSeriesListResponse(List<TvSeriesDto> dtoList) {
        TvSeriesListResponse response = new TvSeriesListResponse();
        List<TvSeriesListItem> tvSeriesItems = tvSeriesDtoListToTvSeriesListItemList(dtoList);
        response.setData(tvSeriesItems);
        return response;
    }

    TvSeriesDto tvSeriesEntityToTvSeriesDto(TvSeriesEntity tvSeriesEntity);

    List<TvSeriesDto> tvSeriesEntityListToTvSeriesDtoList(List<TvSeriesEntity> entityList);

    SingleTvSeries tvSeriesDtoToSingleTvSeries(TvSeriesDto tvSeriesDto);

    default SingleTvSeriesResponse tvSeriesDtoToSingleTvSeriesResponse(TvSeriesDto tvSeriesDto) {
        SingleTvSeriesResponse response = new SingleTvSeriesResponse();
        SingleTvSeries singleTvSeries = tvSeriesDtoToSingleTvSeries(tvSeriesDto);
        response.setData(singleTvSeries);
        return response;
    }

    SeasonListItem seasonEntityToSeasonListItem(SeasonEntity season);

    List<SeasonListItem> seasonEntityListToSeasonListItemList (Set<SeasonEntity> value);

    default SingleTvSeriesSeasons entitiesToSingleTvSeries(Set<SeasonEntity> set) {
        SingleTvSeriesSeasons tvSeriesSeasons = new SingleTvSeriesSeasons();
        tvSeriesSeasons.setData(seasonEntityListToSeasonListItemList(set));
        return tvSeriesSeasons;
    };

    List<TvSeriesEntity> receivedTvSeriesDtoListToTvSeriesEntityList(List<ReceivedTvSeriesDto> list);
    List<SeasonEntity> receivedSeasonDtoListToSeasonEntityList(List<ReceivedSeasonDto> list);
    List<EpisodeEntity> receivedEpisodeDtoListToEpisodeEntityList(List<ReceivedEpisodeDto> list);
}
