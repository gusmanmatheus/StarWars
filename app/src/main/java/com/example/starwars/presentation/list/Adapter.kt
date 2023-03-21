package com.example.starwars.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.databinding.PeopleItemLayoutBinding
import com.example.starwars.presentation.ext.picassoLoading
import com.example.starwars.presentation.model.Item
import com.example.starwars.presentation.model.Movie
import com.example.starwars.presentation.model.People
import com.squareup.picasso.Picasso

class Adapter : RecyclerView.Adapter<Adapter.Holder>(),Filterable {

    var onItemClick: ((Item) -> Unit) = {}

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                val filterResult = FilterResults()
                filterResult.values = filterItem(charString)
                return filterResult
            }
            fun filterItem(constraint: CharSequence?): MutableList<Item> {
                val charString = constraint.toString()
                listFiltered = if (charString.isEmpty()) {
                    data
                } else {
                    val listFilter = mutableListOf<Item>()
                    data.forEach {
                        if (it.key.toLowerCase().contains(charString.toLowerCase()) ||
                            it.key.toLowerCase().contains(charString.toLowerCase())
                        ) {
                            listFilter.add(it)
                        }
                    }
                    listFilter
                }
                return listFiltered
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listFiltered = results?.values as MutableList<Item>
                notifyDataSetChanged()
            }
        }
    }

    fun setValues(data: MutableList<Item>) {
        this.data = data
        listFiltered = data
    }
    var listFiltered = mutableListOf<Item>()

    var data: MutableList<Item> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    inner class Holder(private val binding: PeopleItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                listFiltered.getOrNull(adapterPosition)?.let { item ->
                    onItemClick.invoke(item)
                }
            }
        }

        fun rendle(item: Item) {
            if (item is People) {
                Picasso.get()
                    .picassoLoading(item.image, binding.ilImage, binding.progressBar)
                binding.ilFirstKey.text = "Nome"
                binding.ilFirst.text = item.name
                binding.ilSecondKey.text = "Altura"
                binding.ilSecond.text = item.height
                binding.ilThirdKey.text = "Peso"
                binding.ilThird.text = item.mass
            }
            if (item is Movie){
                Picasso.get()
                    .picassoLoading(item.image, binding.ilImage, binding.progressBar)
                binding.ilFirstKey.text = "Nome"
                binding.ilFirst.text = item.title
                binding.ilSecondKey.text = "Data de lançamento"
                binding.ilSecond.text = item.launchDate
                binding.ilThirdKey.text = "Diretor"
                binding.ilThird.text = item.director
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            PeopleItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.rendle(listFiltered[position])
    }

    override fun getItemCount() = listFiltered.count()

}