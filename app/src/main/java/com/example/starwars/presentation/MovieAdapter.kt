package com.example.starwars.presentation


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.databinding.MovieItemLayoutBinding
import com.example.starwars.databinding.PeopleItemLayoutBinding
import com.example.starwars.presentation.ext.picassoLoading
import com.example.starwars.presentation.model.Movie
import com.example.starwars.presentation.model.People
import com.squareup.picasso.Picasso

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.Holder>() {

    var onItemClick: ((Movie) -> Unit) = {}

    var data: List<Movie> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    inner class Holder(private val binding: MovieItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClick(data[adapterPosition])
            }
        }

        fun rendle(movie: Movie) {
            Picasso.get()
                .picassoLoading(movie.image, binding.ilMovieImage, binding.progressBar)
            binding.ilTitle.text = movie.title
            binding.ilDirector.text = movie.director
            binding.ilLaunch.text = movie.launchDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            MovieItemLayoutBinding.inflate(
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