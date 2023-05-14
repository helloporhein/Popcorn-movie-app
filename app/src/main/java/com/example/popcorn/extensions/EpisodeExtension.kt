package com.example.popcorn.extensions

import com.example.popcorn.constants.IMAGE_BASE_URL
import com.example.popcorn.constants.ImageSize
import com.example.popcorn.data_models.Episode

fun Episode.getStillUrl(size: ImageSize = ImageSize.ORIGINAL): String {
    return "$IMAGE_BASE_URL${size.value}${this.stillPath}"
}
