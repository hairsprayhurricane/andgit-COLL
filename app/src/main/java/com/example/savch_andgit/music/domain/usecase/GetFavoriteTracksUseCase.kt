package com.example.savch_andgit.music.domain.usecase

import com.example.savch_andgit.music.domain.model.Track
import com.example.savch_andgit.music.domain.repository.MusicRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteTracksUseCase(private val repo: MusicRepository) {
    operator fun invoke(): Flow<List<Track>> = repo.getFavorites()
}
