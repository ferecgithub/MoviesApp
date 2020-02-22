package com.ferechamitbeyli.moviesapp.ui.detail.overview

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.ferechamitbeyli.moviesapp.R
import com.ferechamitbeyli.moviesapp.common.BaseFragment
import com.ferechamitbeyli.moviesapp.databinding.FragmentOverviewBinding
import com.ferechamitbeyli.moviesapp.model.movie.MovieResults
import com.ferechamitbeyli.moviesapp.model.videos.MovieVideoResults
import com.ferechamitbeyli.moviesapp.ui.detail.DetailRepository
import com.ferechamitbeyli.moviesapp.util.Constants
import com.ferechamitbeyli.moviesapp.util.gone
import com.ferechamitbeyli.moviesapp.util.visible
import kotlinx.android.synthetic.main.fragment_overview.*

class OverviewFragment : BaseFragment<FragmentOverviewBinding, OverviewViewModel>(), VideosAdapter.OnVideoClickListener {

    private lateinit var videosAdapter: VideosAdapter

    override fun getLayoutRes(): Int = R.layout.fragment_overview

    override fun getViewModel(): Class<OverviewViewModel> = OverviewViewModel::class.java

    companion object {
        private const val MOVIE_KEY = "movie_overview_key"
        fun newInstance(movie: MovieResults?): OverviewFragment {
            val args = Bundle()
            args.putParcelable(MOVIE_KEY, movie)

            val fragment = OverviewFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val movie: MovieResults = arguments?.getParcelable<MovieResults>(MOVIE_KEY) as MovieResults
        val movieId = movie.movieId

        viewModel.getDetails(movieId).observe(this, Observer {
            dataBinding.details = it
        })

        detail_movie_videos_progress.visible()
        movie_videos_recyclerview.gone()

        viewModel.getMovieVideos(movieId).observe(this, Observer {
            videosAdapter = VideosAdapter(this)
            movie_videos_recyclerview.adapter = videosAdapter
            videosAdapter.submitList(it)

            detail_movie_videos_progress.gone()
            movie_videos_recyclerview.visible()
        })
    }

    override fun onVideoClick(videos: MovieVideoResults) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(Constants.YOUTUBE_WATCH_URL + videos.key)
        startActivity(intent)
    }

}