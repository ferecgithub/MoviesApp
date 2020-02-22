package com.ferechamitbeyli.moviesapp.ui.detail

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.ferechamitbeyli.moviesapp.data.local.MovieDao
import com.ferechamitbeyli.moviesapp.data.local.MovieDatabase
import com.ferechamitbeyli.moviesapp.model.movie.MovieResults

class DetailRepository(context: Context) {

    private val db: MovieDatabase by lazy { MovieDatabase.getInstance(context) }
    private val dao: MovieDao by lazy { db.movieDao() }

    fun insertMovie(movie: MovieResults?) {
        InsertAsyncTask(dao).execute(movie)
    }

    fun deleteMovie(movie: MovieResults?) {
        DeleteAsyncTask(dao).execute(movie)
    }

    fun getAllMovies(): LiveData<List<MovieResults>> {
        return dao.getAllMovies()
    }

    fun getSingleMovie(movieId: Int?): LiveData<MovieResults> {
        return dao.getSingleMovie(movieId)
    }


    private class InsertAsyncTask(val dao: MovieDao): AsyncTask<MovieResults, Void, Void>() {
        override fun doInBackground(vararg params: MovieResults?): Void? {
            dao.insertMovie(params[0])
            return null
        }
    }

    private class DeleteAsyncTask(val dao: MovieDao): AsyncTask<MovieResults, Void, Void>() {
        override fun doInBackground(vararg params: MovieResults?): Void? {
            dao.deleteMovie(params[0])
            return null
        }
    }
}