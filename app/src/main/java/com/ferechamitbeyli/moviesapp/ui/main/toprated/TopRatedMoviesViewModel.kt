package com.ferechamitbeyli.moviesapp.ui.main.toprated

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ferechamitbeyli.moviesapp.model.movie.MovieResults
import com.ferechamitbeyli.moviesapp.ui.main.MainRepository

class TopRatedMoviesViewModel: ViewModel() {
    private val repository: MainRepository by lazy { MainRepository() }

    fun getTopRatedMovies(): LiveData<List<MovieResults>>? = repository.getTopRatedMovies()
}