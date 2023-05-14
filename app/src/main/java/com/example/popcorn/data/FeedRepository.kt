package com.example.popcorn.data

import androidx.paging.PagingConfig

object FeedRepository {
    private const val NETWORK_PAGE_SIZE = 20
    private val DEFAULT_PAGING_CONFIG = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
    )
}