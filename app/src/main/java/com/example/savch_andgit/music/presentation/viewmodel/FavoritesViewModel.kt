package com.example.savch_andgit.music.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savch_andgit.music.domain.model.Track
import com.example.savch_andgit.music.domain.usecase.GetFavoriteTracksUseCase
import com.example.savch_andgit.music.domain.usecase.RemoveFavoriteTrackUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(
    getFavorites: GetFavoriteTracksUseCase,
    private val removeFavorite: RemoveFavoriteTrackUseCase
) : ViewModel() {

    val favorites: StateFlow<List<Track>> = getFavorites()
        .map { it }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun remove(track: Track) {
        viewModelScope.launch { removeFavorite(track) }
    }
}
