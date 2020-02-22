package com.ferechamitbeyli.moviesapp.ui.main.favorites

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
import com.ferechamitbeyli.moviesapp.ui.detail.DetailViewModel
import com.ferechamitbeyli.moviesapp.ui.main.MovieAdapter
import com.ferechamitbeyli.moviesapp.util.Constants
import com.ferechamitbeyli.moviesapp.util.gone
import com.ferechamitbeyli.moviesapp.util.visible
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment: BaseVMFragment<DetailViewModel>(), MovieAdapter.OnMovieClickListener {

    private lateinit var adapter: MovieAdapter

    override fun getViewModel(): Class<DetailViewModel> = DetailViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = MovieAdapter()
        favorites_recyclerview.layoutManager = GridLayoutManager(activity, 2)

        adapter.setOnMovieClickListener(this)

        viewModel.getAllMovies().observe(this, Observer {
            adapter.submitList(it)
            favorites_recyclerview.adapter = adapter
            favorites_recyclerview.visible()
            favorites_progressbar.gone()
        })
    }

    override fun onMovieClick(movieResults: MovieResults) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(Constants.EXTRA_MOVIES, movieResults)
        startActivity(intent)
    }
}