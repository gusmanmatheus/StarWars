package com.example.starwars.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.starwars.data.model.TypeItem
import com.example.starwars.databinding.FragmentListSelectorBinding

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

    private fun goToListFragment(item: TypeItem) {
        findNavController().safeNavigate(
            ListSelectorFragmentDirections.actionListSelectorFragmentToListFragment(item)
        )
    }
}