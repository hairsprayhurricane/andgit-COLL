package com.example.savch_andgit.music.domain.repository

import com.example.savch_andgit.music.domain.model.Track
import kotlinx.coroutines.flow.Flow

interface MusicRepository {
    suspend fun searchTracks(query: String): List<Track>
    fun getFavorites(): Flow<List<Track>>
    suspend fun saveFavorite(track: Track)
    suspend fun removeFavorite(track: Track)
    suspend fun isFavorite(id: Long): Boolean
}
