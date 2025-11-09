package com.example.savch_andgit.music.domain.usecase

import com.example.savch_andgit.music.domain.model.Track
import com.example.savch_andgit.music.domain.repository.MusicRepository

class SaveFavoriteTrackUseCase(private val repo: MusicRepository) {
    suspend operator fun invoke(track: Track) = repo.saveFavorite(track)
}
