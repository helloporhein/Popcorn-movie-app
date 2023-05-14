package com.example.popcorn.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.popcorn.data_models.Movie
import com.example.popcorn.databinding.ItemMediaBinding
import com.example.popcorn.databinding.ItemPosterBinding

class FeedMoviesAdapter(private val onItemClick: ((Movie) -> Unit)) :
    PagingDataAdapter<Movie, FeedViewHolder>(FeedDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val binding = ItemPosterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val media = getItem(position)
        holder.bind(media)
    }
}