package com.example.starwars.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.starwars.R
import com.example.starwars.databinding.FragmentDetailsMovieBinding


class DetailsMovieFragment : Fragment() {
    lateinit var binding: FragmentDetailsMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsMovieBinding.inflate(inflater)
         return binding.root
    }

}