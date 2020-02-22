package com.ferechamitbeyli.moviesapp.ui.detail.overview

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ferechamitbeyli.moviesapp.data.remote.ApiClient
import com.ferechamitbeyli.moviesapp.data.remote.ApiService
import com.ferechamitbeyli.moviesapp.model.detail.MovieDetailResponse
import com.ferechamitbeyli.moviesapp.model.videos.MovieVideoResponse
import com.ferechamitbeyli.moviesapp.model.videos.MovieVideoResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverviewRepository {

    private val apiService: ApiService by lazy { ApiClient.getApiService() }

    fun getDetails(movieId: Int): MutableLiveData<MovieDetailResponse> {
        val movieDetailLive: MutableLiveData<MovieDetailResponse> = MutableLiveData()
        apiService.getMovieDetails(movieId).enqueue(object : Callback<MovieDetailResponse> {
            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                Log.e("getDetails",  t.message)
            }

            override fun onResponse(call: Call<MovieDetailResponse>, response: Response<MovieDetailResponse>) {
                movieDetailLive.value = response.body()
            }

        })

        return movieDetailLive
    }

    fun getMovieVideos(movieId: Int): MutableLiveData<List<MovieVideoResults>> {
        val videosLive: MutableLiveData<List<MovieVideoResults>> = MutableLiveData()
        apiService.getMovieVideos(movieId).enqueue(object: Callback<MovieVideoResponse> {
            override fun onFailure(call: Call<MovieVideoResponse>, t: Throwable) {
                Log.e("getMovieVideos",  t.message)
            }

            override fun onResponse(call: Call<MovieVideoResponse>, response: Response<MovieVideoResponse>) {
                videosLive.value = response.body()?.results
            }

        })

        return videosLive
    }
}