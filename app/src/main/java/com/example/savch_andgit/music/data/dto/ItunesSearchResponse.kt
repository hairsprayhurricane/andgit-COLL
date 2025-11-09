package com.example.savch_andgit.music.data.dto

import com.squareup.moshi.Json

data class ItunesSearchResponse(
    @Json(name = "resultCount") val resultCount: Int,
    @Json(name = "results") val results: List<ItunesTrackDto>
)

data class ItunesTrackDto(
    @Json(name = "trackId") val trackId: Long?,
    @Json(name = "trackName") val trackName: String?,
    @Json(name = "artistName") val artistName: String?,
    @Json(name = "artworkUrl100") val artworkUrl100: String?,
    @Json(name = "previewUrl") val previewUrl: String?,
    @Json(name = "trackViewUrl") val trackViewUrl: String?
)
