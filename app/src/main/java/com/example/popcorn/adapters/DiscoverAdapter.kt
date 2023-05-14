package com.example.popcorn.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.popcorn.data_models.Movie
import com.example.popcorn.databinding.ItemFeedHeaderBinding
import com.example.popcorn.databinding.ItemPosterBinding
import com.example.popcorn.extensions.getPosterUrl

class DiscoverAdapter(private val onItemClick: ((Movie) -> Unit)) :
    ListAdapter<Movie, DiscoverViewHolder>(DiscoverDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverViewHolder {
        val binding = ItemFeedHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiscoverViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: DiscoverViewHolder, position: Int) {
        val media = getItem(position)
        holder.bind(media)
    }
}

class DiscoverViewHolder(
    var binding: ItemFeedHeaderBinding,
    private val onItemClick: ((Movie) -> Unit)
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie?) {
        if (movie == null) {
            return
        }

        val posterUrl = movie.getPosterUrl()
        Glide.with(binding.backgroundImage).load(posterUrl).transform(CenterCrop())
            .into(binding.backgroundImage)
        binding.backgroundImage.clipToOutline = true

        itemView.setOnClickListener { onItemClick(movie) }
    }
}

class DiscoverDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}