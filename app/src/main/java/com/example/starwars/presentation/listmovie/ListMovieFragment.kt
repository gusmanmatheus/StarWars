package com.example.starwars.presentation.listmovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwars.databinding.FragmentListMovieBinding
import com.example.starwars.presentation.Adapter
import com.example.starwars.presentation.model.Item
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListMovieFragment : Fragment() {
    val adapter: Adapter by inject()
    private val viewModel: ListMovieViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        disablePageModifyObserver()
        getMovieObserver()
        filterSearchView()
        viewModel.getMovies("1")
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

    }

    private fun getMovieObserver() {
        viewModel.actualMovieListLiveData.observe(viewLifecycleOwner) {
            adapter.setValues(it.toMutableList())
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

    private fun disablePageModifyObserver(){
        viewModel.nextPageLiveData.observe(viewLifecycleOwner) {
            if( it == "0") binding.nextPage.visibility = View.INVISIBLE
        }
        viewModel.previousPageLiveData.observe(viewLifecycleOwner) {
            if( it == "0") binding.previousPage.visibility = View.INVISIBLE
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