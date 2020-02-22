package com.ferechamitbeyli.moviesapp.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ferechamitbeyli.moviesapp.data.remote.ApiClient
import com.ferechamitbeyli.moviesapp.data.remote.ApiService
import com.ferechamitbeyli.moviesapp.model.movie.MovieResponse
import com.ferechamitbeyli.moviesapp.model.movie.MovieResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository {
    private val apiService: ApiService by lazy { ApiClient.getApiService() }

    fun getPopularMovies(): LiveData<List<MovieResults>>? {
        val moviesLiveData: MutableLiveData<List<MovieResults>> = MutableLiveData()
        apiService.getPopularMovies().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("getPopularMovies",  t.message)
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                moviesLiveData.value = response.body()?.results
            }

        })

        return moviesLiveData
    }

    fun getTopRatedMovies(): LiveData<List<MovieResults>>?  {
        val moviesLiveData: MutableLiveData<List<MovieResults>> = MutableLiveData()
        apiService.getTopRatedMovies().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("getTopRatedMovies",  t.message)
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
               moviesLiveData.value = response.body()?.results
            }

        })

        return moviesLiveData
    }
}