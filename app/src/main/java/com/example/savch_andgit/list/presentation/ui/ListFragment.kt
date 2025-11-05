package com.example.savch_andgit.list.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.savch_andgit.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.savch_andgit.list.presentation.adapter.TitlesAdapter

class ListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val rv = view.findViewById<RecyclerView>(R.id.recyclerTitles)
        rv.layoutManager = LinearLayoutManager(requireContext())
        val data = listOf(
            "Привет я список из пункта 4 домашнего тз",
            "Меня никто решил не описывать и вот я здесь",
            "Я есть и не рад этому из за того что происходит подо мной",
            1234567890.toShort().toString().takeIf { false } ?: "",
            "Но зато язык просто выучить"
        )
        rv.adapter = TitlesAdapter(data)
    }
}
