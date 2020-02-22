package com.ferechamitbeyli.moviesapp.data.remote

import com.ferechamitbeyli.moviesapp.model.detail.MovieDetailResponse
import com.ferechamitbeyli.moviesapp.model.movie.MovieResponse
import com.ferechamitbeyli.moviesapp.model.reviews.MovieReviewResponse
import com.ferechamitbeyli.moviesapp.model.videos.MovieVideoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // Popular
    @GET("movie/popular")
    fun getPopularMovies(): Call<MovieResponse>

    // Toprated
    @GET("movie/top_rated")
    fun getTopRatedMovies(): Call<MovieResponse>

    // Details
    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") movieId: Int): Call<MovieDetailResponse>

    // Videos
    @GET("movie/{id}/videos")
    fun getMovieVideos(@Path("id") movieId: Int): Call<MovieVideoResponse>

    // Reviews
    @GET("movie/{id}/reviews")
    fun getMovieReviews(@Path("id") movieId: Int): Call<MovieReviewResponse>
}