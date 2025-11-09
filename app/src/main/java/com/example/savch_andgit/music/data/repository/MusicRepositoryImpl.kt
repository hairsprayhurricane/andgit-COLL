package com.example.savch_andgit.music.data.repository

import com.example.savch_andgit.music.data.local.TrackDao
import com.example.savch_andgit.music.data.mapper.toDomain
import com.example.savch_andgit.music.data.mapper.toEntity
import com.example.savch_andgit.music.data.service.ItunesService
import com.example.savch_andgit.music.domain.model.Track
import com.example.savch_andgit.music.domain.repository.MusicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MusicRepositoryImpl(
    private val service: ItunesService,
    private val dao: TrackDao
) : MusicRepository {
    override suspend fun searchTracks(query: String): List<Track> {
        val resp = withContext(Dispatchers.IO) { service.searchTracks(query) }
        return resp.results.mapNotNull { it.toDomain() }
    }

    override fun getFavorites(): Flow<List<Track>> = dao.getAll().map { list -> list.map { it.toDomain() } }

    override suspend fun saveFavorite(track: Track) {
        dao.insert(track.toEntity())
    }

    override suspend fun removeFavorite(track: Track) {
        dao.delete(track.toEntity())
    }

    override suspend fun isFavorite(id: Long): Boolean = dao.getById(id) != null
}
