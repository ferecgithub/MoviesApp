package com.ferechamitbeyli.moviesapp.ui.detail.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ferechamitbeyli.moviesapp.model.detail.MovieDetailResponse
import com.ferechamitbeyli.moviesapp.model.videos.MovieVideoResults

class OverviewViewModel: ViewModel() {

    private val repository: OverviewRepository by lazy { OverviewRepository() }

    fun getDetails(movieId: Int): LiveData<MovieDetailResponse> = repository.getDetails(movieId)

    fun getMovieVideos(movieId: Int): LiveData<List<MovieVideoResults>> = repository.getMovieVideos(movieId)
}