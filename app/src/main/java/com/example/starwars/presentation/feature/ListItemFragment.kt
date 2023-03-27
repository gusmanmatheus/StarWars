package com.example.starwars.presentation.feature

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwars.R
import com.example.starwars.databinding.FragmentListItemBinding
import com.example.starwars.presentation.adapter.Adapter
import com.example.starwars.presentation.model.Item
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class ListItemFragment() : Fragment() {
    open val viewModel: ViewModelItem by viewModel()
    open val adapter: Adapter by inject()

    protected open lateinit var binding: FragmentListItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getItems("1")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListItemBinding.inflate(inflater)
        setupList()
        clickAdapter()
        pageModify()
        disableAndEnablePageModifyObserver()
        setupObservers()
        disablePageModify()
        filterSearchView()
        return binding.root
    }


    protected open fun setupList() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    protected open fun clickAdapter() {
        adapter.onItemClick = {
            nextActivity(it)
        }
    }

    protected abstract fun nextActivity(item: Item)

    protected open fun setupObservers() {
        viewModel.actualItemListLiveData.observe(viewLifecycleOwner) {
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

    protected open fun pageModify() {
        binding.nextPage.setOnClickListener {
            viewModel.getItems(viewModel.nextPageLiveData.value ?: "0")
        }
        binding.previousPage.setOnClickListener {
            viewModel.getItems(viewModel.previousPageLiveData.value ?: "0")
        }
    }

    protected open fun disablePageModify() {
        binding.nextPage.visibility = View.INVISIBLE
        binding.previousPage.visibility = View.INVISIBLE
    }

    protected open fun disableAndEnablePageModifyObserver() {
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

    protected open fun filterSearchView() {
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