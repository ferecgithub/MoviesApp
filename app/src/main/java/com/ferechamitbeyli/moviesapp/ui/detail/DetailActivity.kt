package com.ferechamitbeyli.moviesapp.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.ferechamitbeyli.moviesapp.R
import com.ferechamitbeyli.moviesapp.common.BaseActivity
import com.ferechamitbeyli.moviesapp.common.ViewPagerAdapter
import com.ferechamitbeyli.moviesapp.databinding.ActivityDetailBinding
import com.ferechamitbeyli.moviesapp.model.movie.MovieResults
import com.ferechamitbeyli.moviesapp.ui.detail.overview.OverviewFragment
import com.ferechamitbeyli.moviesapp.ui.detail.reviews.ReviewsFragment
import com.ferechamitbeyli.moviesapp.util.Constants
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {

    private var movie: MovieResults? = null

    private var isFav: Boolean? = null

    override fun getLayoutRes(): Int = R.layout.activity_detail

    override fun getViewModel(): Class<DetailViewModel> = DetailViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupUI()

        intent.extras.let {
            movie = it?.getParcelable(Constants.EXTRA_MOVIES)
            setupViewPager(movie)
            fabBehaviour(movie)
            detail_tabs.setupWithViewPager(detail_viewpager)

            checkFav()

            favorite_fab.setOnClickListener { favorite() }

            dataBinding.movie = movie
        }
    }

    private fun favorite() {
        if(isFav!!) {
            viewModel.deleteMovie(movie)
            Toast.makeText(this, "Favorilerden çıkarıldı", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.insertMovie(movie)
            Toast.makeText(this, "Favorilere eklendi", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkFav() {
        viewModel.getSingleMovie(movie?.movieId).observe(this, Observer {
            if(it != null) {
                favorite_fab.setImageResource(R.drawable.ic_star)
                isFav = true
            } else {
                favorite_fab.setImageResource(R.drawable.ic_star_border)
                isFav = false
            }
        })
    }

    private fun fabBehaviour(movie: MovieResults?) {
        detail_appbar_layout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffsett ->
            if(Math.abs(verticalOffsett) - appBarLayout.totalScrollRange == 0) {
                favorite_fab.hide()
                supportActionBar?.setDisplayShowTitleEnabled(true)
                detail_toolbar.title = movie?.title
            } else {
                favorite_fab.show()
                supportActionBar?.setDisplayShowTitleEnabled(false)
                detail_toolbar.title = " "
            }
        })

        detail_collapsing_toolbarlayout.setExpandedTitleColor(resources.getColor(android.R.color.transparent))
    }

    private fun setupUI() {
        setSupportActionBar(detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }


    private fun setupViewPager(movie: MovieResults?) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.apply {
            addFragment(OverviewFragment.newInstance(movie), "Overview")
            addFragment(ReviewsFragment(), "Reviews")
        }
        detail_viewpager.adapter = adapter
    }
}