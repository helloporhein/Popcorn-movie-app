package com.example.popcorn.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.popcorn.data_models.Movie
import com.example.popcorn.databinding.ItemFeedHeaderBinding
import com.example.popcorn.databinding.ItemPosterBinding

class DiscoverMoviesAdapter(private val onItemClick: ((Movie) -> Unit)) :
    PagingDataAdapter<Movie, DiscoverViewHolder>(DiscoverDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverViewHolder {
        val binding = ItemFeedHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiscoverViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: DiscoverViewHolder, position: Int) {
        val media = getItem(position)
        holder.bind(media)
    }
}