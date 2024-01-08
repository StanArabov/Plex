package com.cleverpine.plex.mapper;

import com.cleverpine.plex.dto.TvSeriesDto;
import com.cleverpine.plex.entity.future.SeasonEntity;
import com.cleverpine.plex.entity.future.TvSeriesEntity;
import com.cleverpine.plex.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper
public interface TvSeriesMapper {
    TvSeriesMapper INSTANCE = Mappers.getMapper(TvSeriesMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    TvSeriesListItem tvSeriesDtoToTvSeriesListItem(TvSeriesDto dto);

    List<TvSeriesListItem> tvSeriesDtoListToTvSeriesListItemList(List<TvSeriesDto> dtoList);

    default TvSeriesListResponse tvSeriesDtoListToTvSeriesListResponse(List<TvSeriesDto> dtoList) {
        TvSeriesListResponse response = new TvSeriesListResponse();
        List<TvSeriesListItem> tvSeriesItems = INSTANCE.tvSeriesDtoListToTvSeriesListItemList(dtoList);
        response.setData(tvSeriesItems);
        return response;
    }

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "rating", target = "rating")
    @Mapping(source = "releaseDate", target = "releaseDate")
    @Mapping(source = "duration", target = "duration")
    @Mapping(source = "year", target = "year")
    @Mapping(source = "director", target = "director")
    @Mapping(source = "writer", target = "writer")
    @Mapping(source = "genres", target = "genres")
    @Mapping(source = "stars", target = "stars")
    @Mapping(source = "seasons", target = "seasons")
    TvSeriesDto tvSeriesEntityToTvSeriesDto(TvSeriesEntity tvSeriesEntity);

    List<TvSeriesDto> tvSeriesEntityListToTvSeriesDtoList(List<TvSeriesEntity> entityList);

    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "rating", target = "rating")
    @Mapping(source = "releaseDate", target = "releaseDate")
    @Mapping(source = "duration", target = "duration")
    @Mapping(source = "year", target = "year")
    @Mapping(source = "director", target = "director")
    @Mapping(source = "writer", target = "writer")
    @Mapping(source = "genres", target = "genres")
    @Mapping(source = "stars", target = "stars")
    @Mapping(source = "seasons", target = "seasons")
    SingleTvSeries tvSeriesDtoToSingleTvSeries(TvSeriesDto tvSeriesDto);

    default SingleTvSeriesResponse tvSeriesDtoToSingleTvSeriesResponse(TvSeriesDto tvSeriesDto) {
        SingleTvSeriesResponse response = new SingleTvSeriesResponse();
        SingleTvSeries singleTvSeries = INSTANCE.tvSeriesDtoToSingleTvSeries(tvSeriesDto);
        response.setData(singleTvSeries);
        return response;
    }

    @Mapping(source = "id", target = "id")
    @Mapping(source = "number", target = "number")
    SeasonListItem seasonEntityToSeasonListItem(SeasonEntity season);

    List<SeasonListItem> seasonEntityListToSeasonListItemList (Set<SeasonEntity> value);

    default SingleTvSeriesSeasons entitiesToSingleTvSeries(Set<SeasonEntity> set) {
        SingleTvSeriesSeasons tvSeriesSeasons = new SingleTvSeriesSeasons();
        tvSeriesSeasons.setData(INSTANCE.seasonEntityListToSeasonListItemList(set));
        return tvSeriesSeasons;
    };
}
