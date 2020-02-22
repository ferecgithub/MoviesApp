package com.ferechamitbeyli.moviesapp.ui.main.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ferechamitbeyli.moviesapp.model.movie.MovieResults
import com.ferechamitbeyli.moviesapp.ui.main.MainRepository

class PopularMoviesViewModel: ViewModel() {
    private val repository: MainRepository by lazy { MainRepository() }

    fun getPopularMovies(): LiveData<List<MovieResults>>? = repository.getPopularMovies()
}