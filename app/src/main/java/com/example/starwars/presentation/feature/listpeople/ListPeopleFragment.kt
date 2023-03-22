package com.example.starwars.presentation.feature.listpeople

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwars.databinding.FragmentListPeopleBinding
import com.example.starwars.presentation.adapter.Adapter
import com.example.starwars.presentation.model.Item
import com.example.starwars.presentation.model.People
import com.example.starwars.presentation.safeNavigate
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListPeopleFragment : Fragment() {
    private val adapter: Adapter by inject()
    private val viewModel: ListPeopleViewModel by viewModel()

    private lateinit var binding: FragmentListPeopleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getPeoples("1")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListPeopleBinding.inflate(inflater)
        setupList()
        clickAdapter()
        pageModify()
        disableAndEnablePageModifyObserver()
        setupObservers()
        disablePageModify()
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
        if (item is People) {
            findNavController().safeNavigate(
                ListPeopleFragmentDirections.actionListPeopleFragmentToDetailsPeopleFragment(item)
            )
        }
    }

    private fun setupObservers() {
        viewModel.actualPeopleListLiveData.observe(viewLifecycleOwner) {
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
        binding.nextPage.setOnClickListener {
            viewModel.getPeoples(viewModel.nextPageLiveData.value ?: "0")
        }
        binding.previousPage.setOnClickListener {
            viewModel.getPeoples(viewModel.previousPageLiveData.value ?: "0")
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
