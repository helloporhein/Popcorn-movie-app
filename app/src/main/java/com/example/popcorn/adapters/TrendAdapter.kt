package com.example.popcorn.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.popcorn.data_models.Movie
import com.example.popcorn.databinding.ItemPosterBinding
import com.example.popcorn.extensions.getPosterUrl

class TrendAdapter(private val onItemClick: ((Movie) -> Unit)) :
    ListAdapter<Movie, TrendViewHolder>(TrendDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendViewHolder {
        val binding = ItemPosterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: TrendViewHolder, position: Int) {
        val media = getItem(position)
        holder.bind(media)
    }
}

class TrendViewHolder(
    var binding: ItemPosterBinding,
    private val onItemClick: ((Movie) -> Unit)
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie?) {
        if (movie == null) {
            return
        }

        val posterUrl = movie.getPosterUrl()
        Glide.with(binding.posterImage).load(posterUrl).transform(CenterCrop())
            .into(binding.posterImage)
        binding.posterImage.clipToOutline = true

        itemView.setOnClickListener { onItemClick(movie) }
    }
}

class TrendDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}