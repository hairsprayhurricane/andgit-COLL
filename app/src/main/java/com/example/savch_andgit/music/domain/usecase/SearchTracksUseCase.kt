package com.example.savch_andgit.music.domain.usecase

import com.example.savch_andgit.music.domain.model.Track
import com.example.savch_andgit.music.domain.repository.MusicRepository

class SearchTracksUseCase(private val repo: MusicRepository) {
    suspend operator fun invoke(query: String): List<Track> = repo.searchTracks(query)
}
