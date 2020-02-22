package com.ferechamitbeyli.moviesapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ferechamitbeyli.moviesapp.model.movie.MovieResults

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieResults?)

    @Delete
    fun deleteMovie(movie: MovieResults?)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<MovieResults>>

    @Query("SELECT * FROM movies WHERE movieId= :movieId")
    fun getSingleMovie(movieId: Int?): LiveData<MovieResults>
}