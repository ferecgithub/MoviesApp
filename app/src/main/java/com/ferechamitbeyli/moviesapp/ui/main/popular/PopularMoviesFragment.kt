package com.ferechamitbeyli.moviesapp.ui.main.popular

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.ferechamitbeyli.moviesapp.R
import com.ferechamitbeyli.moviesapp.common.BaseVMFragment
import com.ferechamitbeyli.moviesapp.model.movie.MovieResults
import com.ferechamitbeyli.moviesapp.ui.detail.DetailActivity
import com.ferechamitbeyli.moviesapp.ui.main.MovieAdapter
import com.ferechamitbeyli.moviesapp.util.Constants
import com.ferechamitbeyli.moviesapp.util.gone
import com.ferechamitbeyli.moviesapp.util.visible
import kotlinx.android.synthetic.main.fragment_popular_movies.*

class PopularMoviesFragment: BaseVMFragment<PopularMoviesViewModel>(), MovieAdapter.OnMovieClickListener {

    private lateinit var adapter: MovieAdapter

    override fun getViewModel(): Class<PopularMoviesViewModel> = PopularMoviesViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_popular_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = MovieAdapter()
        popular_recyclerview.layoutManager = GridLayoutManager(activity, 2)

        adapter.setOnMovieClickListener(this)

        viewModel.getPopularMovies()?.observe(this, Observer {
            adapter.submitList(it)
            popular_recyclerview.adapter = adapter
            popular_recyclerview.visible()
            popular_progressbar.gone()
        })
    }


    override fun onMovieClick(movieResults: MovieResults) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(Constants.EXTRA_MOVIES, movieResults)
        startActivity(intent)
    }
}