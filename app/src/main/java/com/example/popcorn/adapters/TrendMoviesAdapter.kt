package com.example.popcorn.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.popcorn.data_models.Movie
import com.example.popcorn.databinding.ItemPosterBinding

class TrendMoviesAdapter(private val onItemClick: ((Movie) -> Unit)) :
    PagingDataAdapter<Movie, TrendViewHolder>(TrendDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendViewHolder {
        val binding = ItemPosterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: TrendViewHolder, position: Int) {
        val media = getItem(position)
        holder.bind(media)
    }
}