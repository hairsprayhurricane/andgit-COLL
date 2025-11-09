package com.example.savch_andgit.music.data.mapper

import com.example.savch_andgit.music.data.dto.ItunesTrackDto
import com.example.savch_andgit.music.data.local.TrackEntity
import com.example.savch_andgit.music.domain.model.Track

fun ItunesTrackDto.toDomain(): Track? {
    val id = trackId ?: return null
    return Track(
        id = id,
        name = trackName.orEmpty(),
        artist = artistName.orEmpty(),
        artworkUrl = artworkUrl100,
        previewUrl = previewUrl,
        trackUrl = trackViewUrl
    )
}

fun Track.toEntity(): TrackEntity = TrackEntity(
    id = id,
    name = name,
    artist = artist,
    artworkUrl = artworkUrl,
    previewUrl = previewUrl,
    trackUrl = trackUrl
)

fun TrackEntity.toDomain(): Track = Track(
    id = id,
    name = name,
    artist = artist,
    artworkUrl = artworkUrl,
    previewUrl = previewUrl,
    trackUrl = trackUrl
)
