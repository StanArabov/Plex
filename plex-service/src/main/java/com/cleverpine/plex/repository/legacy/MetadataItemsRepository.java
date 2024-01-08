package com.cleverpine.plex.repository.legacy;

import com.cleverpine.plex.entity.legacy.MetadataItems;
import com.cleverpine.plex.projection.MovieProjection;
import com.cleverpine.plex.projection.SeasonsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface MetadataItemsRepository extends JpaRepository<MetadataItems, Integer> {

    @Query("SELECT " +
            "meta.id as metadataId, " +
            "meta.title AS title, " +
            "meta.summary as description, " +
            "meta.rating as rating, " +
            "meta.originallyAvailableAt as releaseDate, " +
            "mediaParts.duration AS duration, " +
            "meta.year as year, " +
            "meta.metadataType as type, " +
            "meta.index as index, " +
            "GROUP_CONCAT(DISTINCT(CASE WHEN tags.tagType = 4 THEN cast(tags.tag AS char) END)) AS director, " +
            "GROUP_CONCAT(DISTINCT(CASE WHEN tags.tagType = 5 THEN cast(tags.tag AS char) END)) AS writer, " +
            "GROUP_CONCAT(DISTINCT(CASE WHEN tags.tagType = 6 THEN cast(tags.tag AS char) END)) AS stars, " +
            "GROUP_CONCAT(DISTINCT(CASE WHEN tags.tagType = 1 THEN cast(tags.tag AS char) END)) AS genres, " +
            "GROUP_CONCAT(DISTINCT(CASE WHEN mediaStreams.streamTypeId = 1 THEN cast(mediaStreams.language AS char) END)) AS audio, " +
            "GROUP_CONCAT(DISTINCT(CASE WHEN mediaStreams.streamTypeId = 3 THEN cast(mediaStreams.language AS char) END)) AS subtitles " +
            "FROM MetadataItems meta " +
            "LEFT JOIN MediaItems mediaItems on meta.id = mediaItems.metadataItemId " +
            "LEFT JOIN MediaParts mediaParts on mediaItems.id = mediaParts.mediaItemId " +
            "LEFT JOIN MediaStreams mediaStreams on mediaItems.id = mediaStreams.mediaItemId " +
            "LEFT JOIN Taggings taggings on meta.id = taggings.metadataItemId " +
            "LEFT JOIN StreamTypes streamTypes on mediaStreams.streamTypeId = streamTypes.id " +
            "LEFT JOIN Tags tags on taggings.tagId = tags.id " +
            "WHERE meta.metadataType in (1,2) " +
            "AND meta.librarySectionId NOT IN (9, 10, 13, 14)" +
            "GROUP BY meta.title"
    )
    List<MovieProjection> findMetadataAndMedia();

    @Query("SELECT meta.id as metadataId, meta.index as number from MetadataItems meta where meta.parentId = :parentId")
    Set<SeasonsProjection> getSeasons(Integer parentId);

    @Query("SELECT " +
            "meta.id as metadataId, " +
            "meta.title AS title, " +
            "meta.summary as description, " +
            "meta.rating as rating, " +
            "meta.originallyAvailableAt as releaseDate, " +
            "mediaParts.duration AS duration, " +
            "meta.year as year, " +
            "meta.metadataType as type, " +
            "meta.index as index, " +
            "meta.parentId as parentId, " +
            "GROUP_CONCAT(DISTINCT(CASE WHEN tags.tagType = 4 THEN cast(tags.tag AS char) END)) AS director, " +
            "GROUP_CONCAT(DISTINCT(CASE WHEN tags.tagType = 5 THEN cast(tags.tag AS char) END)) AS writer, " +
            "GROUP_CONCAT(DISTINCT(CASE WHEN tags.tagType = 6 THEN cast(tags.tag AS char) END)) AS stars, " +
            "GROUP_CONCAT(DISTINCT(CASE WHEN tags.tagType = 1 THEN cast(tags.tag AS char) END)) AS genres, " +
            "GROUP_CONCAT(DISTINCT(CASE WHEN mediaStreams.streamTypeId = 1 THEN cast(mediaStreams.language AS char) END)) AS audio, " +
            "GROUP_CONCAT(DISTINCT(CASE WHEN mediaStreams.streamTypeId = 3 THEN cast(mediaStreams.language AS char) END)) AS subtitles " +
            "FROM MetadataItems meta " +
            "LEFT JOIN MediaItems mediaItems on meta.id = mediaItems.metadataItemId " +
            "LEFT JOIN MediaParts mediaParts on mediaItems.id = mediaParts.mediaItemId " +
            "LEFT JOIN MediaStreams mediaStreams on mediaItems.id = mediaStreams.mediaItemId " +
            "LEFT JOIN Taggings taggings on meta.id = taggings.metadataItemId " +
            "LEFT JOIN StreamTypes streamTypes on mediaStreams.streamTypeId = streamTypes.id " +
            "LEFT JOIN Tags tags on taggings.tagId = tags.id " +
            "WHERE meta.parentId in :seasonIds " +
            "GROUP BY meta.title")
    List<MovieProjection> findSeasonEpisodes(Integer[] seasonIds);
}
