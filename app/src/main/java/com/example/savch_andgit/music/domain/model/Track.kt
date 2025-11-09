package com.example.savch_andgit.music.domain.model

data class Track(
    val id: Long,
    val name: String,
    val artist: String,
    val artworkUrl: String?,
    val previewUrl: String?,
    val trackUrl: String?
)
