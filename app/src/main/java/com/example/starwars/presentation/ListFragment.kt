package com.example.starwars.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.starwars.R
import com.example.starwars.databinding.FragmentListBinding
import com.example.starwars.presentation.model.Movie
import com.example.starwars.presentation.model.People


class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater)
        return binding.root
    }

    private fun goToDetailsMovieFragment(movie: Movie) {
        ListFragmentDirections.actionListFragmentToDetailsMovieFragment(movie)
    }

    private fun goToDetailsPeopleFragment(people: People) {
        ListFragmentDirections.actionListFragmentToDetailsPeopleFragment(people)
    }
}
