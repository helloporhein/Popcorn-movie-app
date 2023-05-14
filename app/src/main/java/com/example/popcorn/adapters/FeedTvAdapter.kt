package com.example.popcorn.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.popcorn.data_models.TvShow
import com.example.popcorn.databinding.ItemPosterBinding
import com.example.popcorn.extensions.getPosterUrl

class FeedTvAdapter(private val onItemClick: ((TvShow) -> Unit)) :
    ListAdapter<TvShow, FeedTvShowViewHolder>(FeedTvShowDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedTvShowViewHolder {
        val binding = ItemPosterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedTvShowViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: FeedTvShowViewHolder, position: Int) {
        val media = getItem(position)
        holder.bind(media)
    }
}

class FeedTvShowViewHolder(
    var binding: ItemPosterBinding,
    private val onItemClick: ((TvShow) -> Unit),
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(tvShow: TvShow?) {
        if (tvShow == null) {
            return
        }

        val posterUrl = tvShow.getPosterUrl()
        Glide.with(binding.posterImage).load(posterUrl).transform(CenterCrop())
            .into(binding.posterImage)
        binding.posterImage.clipToOutline = true
        itemView.setOnClickListener { onItemClick(tvShow) }
    }
}

class FeedTvShowDiffCallback : DiffUtil.ItemCallback<TvShow>() {
    override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
        return oldItem == newItem
    }
}