package com.example.popcorn.util

import com.example.popcorn.data_models.Movie


sealed class ApiState{
    object Loading : ApiState()
    class Failure(val msg:Throwable) : ApiState()
    class Success(val data:List<Movie>) : ApiState()
    object Empty : ApiState()
}
