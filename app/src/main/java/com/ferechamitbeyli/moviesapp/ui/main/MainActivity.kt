package com.ferechamitbeyli.moviesapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ferechamitbeyli.moviesapp.R
import com.ferechamitbeyli.moviesapp.common.ViewPagerAdapter
import com.ferechamitbeyli.moviesapp.ui.main.favorites.FavoritesFragment
import com.ferechamitbeyli.moviesapp.ui.main.popular.PopularMoviesFragment
import com.ferechamitbeyli.moviesapp.ui.main.toprated.TopRatedMoviesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
    }

    private fun setupUI() {
        setSupportActionBar(main_toolbar)
        setupViewPager()
        main_tabs.setupWithViewPager(main_viewpager)
    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.apply {
            addFragment(PopularMoviesFragment(), "Popular")
            addFragment(TopRatedMoviesFragment(), "Top Rated")
            addFragment(FavoritesFragment(), "Favorites")
        }
        main_viewpager.adapter = adapter
        main_viewpager.offscreenPageLimit = 3
    }
}