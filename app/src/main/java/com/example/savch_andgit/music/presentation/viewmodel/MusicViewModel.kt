package com.example.savch_andgit.music.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savch_andgit.music.domain.model.Track
import com.example.savch_andgit.music.domain.usecase.GetFavoriteTracksUseCase
import com.example.savch_andgit.music.domain.usecase.RemoveFavoriteTrackUseCase
import com.example.savch_andgit.music.domain.usecase.SaveFavoriteTrackUseCase
import com.example.savch_andgit.music.domain.usecase.SearchTracksUseCase
import com.example.savch_andgit.music.presentation.state.MusicState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MusicViewModel(
    private val searchTracks: SearchTracksUseCase,
    private val saveFavorite: SaveFavoriteTrackUseCase,
    private val getFavorites: GetFavoriteTracksUseCase,
    private val removeFavorite: RemoveFavoriteTrackUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MusicState())
    val state: StateFlow<MusicState> = _state

    private var favoritesJob: Job? = null

    init {
        observeFavorites()
    }

    fun updateQuery(q: String) {
        _state.value = _state.value.copy(query = q)
    }

    fun search() {
        val q = _state.value.query.trim()
        if (q.isEmpty()) return
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val results = searchTracks(q)
                _state.value = _state.value.copy(isLoading = false, results = results)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, error = e.message ?: "search_error")
            }
        }
    }

    fun toggleFavorite(track: Track, isFavorite: Boolean) {
        viewModelScope.launch {
            if (isFavorite) removeFavorite(track) else saveFavorite(track)
        }
    }

    private fun observeFavorites() {
        favoritesJob?.cancel()
        favoritesJob = getFavorites()
            .catch { /* ignore */ }
            .onEach { favorites ->
                val favIds = favorites.map { it.id }.toSet()
                _state.value = _state.value.copy(favoritesIds = favIds)
            }
            .launchIn(viewModelScope)
    }
}
