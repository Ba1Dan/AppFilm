package ru.baiganov.appfilm.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.baiganov.appfilm.R
import ru.baiganov.appfilm.adapter.ActorsAdapter
import ru.baiganov.appfilm.adapter.MoviesAdapter
import ru.baiganov.appfilm.domain.ActorsDataSource
import ru.baiganov.appfilm.domain.MoviesDataSource

class FragmentMoviesDetails : Fragment() {

    private lateinit var adapter: ActorsAdapter
    private var recyclerActors: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.tv_back).apply {
            setOnClickListener{
                fragmentManager?.popBackStack()
            }
        }
        recyclerActors = view.findViewById(R.id.rv_actors)
        adapter = ActorsAdapter()
        recyclerActors?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerActors?.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        updateData()
    }

    override fun onDetach() {
        recyclerActors = null
        super.onDetach()
    }

    private fun updateData() {
        (recyclerActors?.adapter as? ActorsAdapter)?.apply {
            onBindActors(ActorsDataSource().getActors(requireContext()))
        }
    }
}