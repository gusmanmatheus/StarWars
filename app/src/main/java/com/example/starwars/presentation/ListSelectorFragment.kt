package com.example.starwars.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.starwars.data.model.TypeItem
import com.example.starwars.databinding.FragmentListSelectorBinding
import com.example.starwars.presentation.ext.picassoLoading
import com.squareup.picasso.Picasso

class ListSelectorFragment : Fragment() {
    private lateinit var binding: FragmentListSelectorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListSelectorBinding.inflate(inflater)
        setupClicks()
        setImage()
        return binding.root
    }

    private fun setupClicks() {
        binding.moviesImage.setOnClickListener {
            goToListFragment(TypeItem.MOVIES)
        }
        binding.peopleImage.setOnClickListener {
            goToListFragment(TypeItem.PEOPLE)
        }
    }

    private fun setImage() {
        Picasso.get()
            .load("https://starwars-visualguide.com/assets/img/categories/films.jpg")
            .into(binding.moviesImage)
        Picasso.get()
            .load("https://starwars-visualguide.com/assets/img/categories/character.jpg")
            .into(binding.peopleImage)
    }

    private fun goToListFragment(item: TypeItem) {
        findNavController().safeNavigate(
            ListSelectorFragmentDirections.actionListSelectorFragmentToListFragment(item)
        )
    }
}