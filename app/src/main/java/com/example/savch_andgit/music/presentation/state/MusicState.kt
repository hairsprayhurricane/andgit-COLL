package com.example.savch_andgit.music.presentation.state

import com.example.savch_andgit.music.domain.model.Track

data class MusicState(
    val query: String = "",
    val isLoading: Boolean = false,
    val results: List<Track> = emptyList(),
    val error: String? = null,
    val favoritesIds: Set<Long> = emptySet()
)
