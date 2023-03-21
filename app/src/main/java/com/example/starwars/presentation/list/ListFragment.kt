package com.example.starwars.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwars.data.model.TypeItem
import com.example.starwars.databinding.FragmentListBinding
import com.example.starwars.presentation.model.Item
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {
    private val adapter: Adapter by inject()
    private val viewModel:ListViewModel by viewModel()
    private val navArgs :ListFragmentArgs by navArgs()

    private lateinit var binding: FragmentListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater)
        setupList()
        clickAdapter()
        pageModify()
        disablePageModifyObserver()
        getPeopleObserver()
        filterSearchView()
        viewModel.getPeoples("1")
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
    private fun getPeopleObserver(){
        viewModel.actualPeopleListLiveData.observe(viewLifecycleOwner){
            adapter.setValues(it.toMutableList())
        }
    }

    private fun pageModify(){
        binding.nextPage.setOnClickListener {
            viewModel.getPeoples(viewModel.nextPageLiveData.value?:"0")
        }
        binding.previousPage.setOnClickListener {
            viewModel.getPeoples(viewModel.previousPageLiveData.value?:"0")
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
