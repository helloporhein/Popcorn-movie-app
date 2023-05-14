package com.example.popcorn.extensions

import com.example.popcorn.network.models.TvDetailsResponse

fun TvDetailsResponse.getInitialSeasonIndex(): Int {
    if (this.seasons.isNotEmpty()) {
        var initialSeasonIndex = this.seasons.indexOfFirst { it.seasonNumber > 0 }
        if (initialSeasonIndex == -1) {
            initialSeasonIndex = 0
        }
        return initialSeasonIndex;
    }
    return -1
}