package com.example.savch_andgit.music.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracks")
data class TrackEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val artist: String,
    val artworkUrl: String?,
    val previewUrl: String?,
    val trackUrl: String?
)
