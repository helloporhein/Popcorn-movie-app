package com.example.popcorn.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.popcorn.data_models.TvShow
import com.example.popcorn.databinding.ItemPosterBinding

class FeedTvShowsAdapter(private val onItemClick: ((TvShow) -> Unit)) :
    PagingDataAdapter<TvShow, FeedTvShowViewHolder>(FeedTvShowDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedTvShowViewHolder {
        val binding = ItemPosterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedTvShowViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: FeedTvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        holder.bind(tvShow)
    }
}