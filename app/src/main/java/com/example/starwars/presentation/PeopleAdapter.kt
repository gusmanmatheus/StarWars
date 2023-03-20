package com.example.starwars.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.databinding.PeopleItemLayoutBinding
import com.example.starwars.presentation.ext.picassoLoading
import com.example.starwars.presentation.model.People
import com.squareup.picasso.Picasso

class PeopleAdapter : RecyclerView.Adapter<PeopleAdapter.Holder>() {

    var onItemClick: ((People) -> Unit) = {}

    var data: List<People> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    inner class Holder(private val binding: PeopleItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClick(data[adapterPosition])
            }
        }

        fun rendle(people: People) {
            Picasso.get()
                .picassoLoading(people.image, binding.ilPeopleImage, binding.progressBar)
            binding.ilName.text = people.name
            binding.ilHeight.text = people.height
            binding.ilMassKey.text = people.mass
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
        holder.rendle(data[position])
    }

    override fun getItemCount() = data.count()

}