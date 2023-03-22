package com.example.starwars.presentation.feature.listmovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwars.databinding.FragmentListMovieBinding
import com.example.starwars.presentation.adapter.Adapter
import com.example.starwars.presentation.model.Item
import com.example.starwars.presentation.model.Movie
import com.example.starwars.presentation.safeNavigate
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListMovieFragment : Fragment() {
    val adapter: Adapter by inject()
    private val viewModel: ListMovieViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovies("1")
    }

    private lateinit var binding: FragmentListMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListMovieBinding.inflate(inflater)
        setupList()
        clickAdapter()
        pageModify()
        disableAndEnablePageModifyObserver()
        disablePageModify()
        setupObservers()
        filterSearchView()
        return binding.root
    }


    private fun setupList() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun clickAdapter() {
        adapter.onItemClick = {
            nextActivity(it)
        }
    }

    private fun nextActivity(item: Item) {
        if (item is Movie) {
            findNavController().safeNavigate(
                ListMovieFragmentDirections.actionListMovieFragmentToDetailsMovieFragment(item)
            )
        }
    }

    private fun setupObservers() {
        viewModel.actualMovieListLiveData.observe(viewLifecycleOwner) {
            binding.requestError.visibility = View.INVISIBLE
            adapter.setValues(it.toMutableList())
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) {
                disablePageModify()
            }
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.requestError.observe(viewLifecycleOwner) {
            disablePageModify()
            binding.requestError.visibility = View.VISIBLE
            binding.requestError.text = it
        }
    }

    private fun pageModify() {
        binding.previousPage.setOnClickListener {
            viewModel.getMovies(viewModel.previousPageLiveData.value ?: "0")
        }
        binding.nextPage.setOnClickListener {
            viewModel.getMovies(viewModel.nextPageLiveData.value ?: "0")
        }
    }

    private fun disablePageModify() {
        binding.nextPage.visibility = View.INVISIBLE
        binding.previousPage.visibility = View.INVISIBLE
    }

    private fun disableAndEnablePageModifyObserver() {
        viewModel.nextPageLiveData.observe(viewLifecycleOwner) {
            if (it == "0") {
                binding.nextPage.visibility = View.INVISIBLE
            } else
                binding.nextPage.visibility = View.VISIBLE
        }
        viewModel.previousPageLiveData.observe(viewLifecycleOwner) {
            if (it == "0") {
                binding.previousPage.visibility = View.INVISIBLE
            } else
                binding.previousPage.visibility = View.VISIBLE
        }
    }


    private fun filterSearchView() {
        binding.searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.filter.filter(newText)
                    return true
                }
            }
        )
    }

}