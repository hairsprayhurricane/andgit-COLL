package com.example.savch_andgit.music.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.savch_andgit.R
import com.example.savch_andgit.music.presentation.adapter.MusicAdapter
import com.example.savch_andgit.music.presentation.viewmodel.MusicViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController

class MusicFragment : Fragment() {
    private val viewModel: MusicViewModel by viewModel()

    private lateinit var etQuery: EditText
    private lateinit var btnSearch: Button
    private lateinit var btnOpenFavorites: Button
    private lateinit var rvTracks: RecyclerView
    private lateinit var adapter: MusicAdapter
    private lateinit var tvLoading: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_music, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        etQuery = view.findViewById(R.id.etQuery)
        btnSearch = view.findViewById(R.id.btnSearch)
        btnOpenFavorites = view.findViewById(R.id.btnOpenFavorites)
        rvTracks = view.findViewById(R.id.rvTracks)
        tvLoading = view.findViewById(R.id.tvLoading)

        adapter = MusicAdapter(emptyList(), emptySet()) { track, isFavorite ->
            viewModel.toggleFavorite(track, isFavorite)
        }
        rvTracks.layoutManager = LinearLayoutManager(requireContext())
        rvTracks.adapter = adapter

        btnSearch.setOnClickListener {
            viewModel.updateQuery(etQuery.text?.toString().orEmpty())
            viewModel.search()
        }

        btnOpenFavorites.setOnClickListener {
            findNavController().navigate(R.id.action_music_to_favorites)
        }

        etQuery.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                btnSearch.performClick()
                true
            } else false
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    etQuery.setText(state.query)
                    adapter.submit(state.results, state.favoritesIds)
                    tvLoading.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                    btnSearch.isEnabled = !state.isLoading
                }
            }
        }
    }
}
