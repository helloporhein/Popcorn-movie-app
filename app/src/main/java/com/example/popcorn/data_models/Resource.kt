package com.example.popcorn.data_models

data class Resource<T>(val isLoading: Boolean, val data: T?, val error: String?)
