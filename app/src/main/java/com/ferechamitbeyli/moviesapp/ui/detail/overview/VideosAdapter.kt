package com.ferechamitbeyli.moviesapp.ui.detail.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ferechamitbeyli.moviesapp.databinding.ItemVideoBinding
import com.ferechamitbeyli.moviesapp.model.videos.MovieVideoResults

class VideosAdapter(private val onVideoClickListener: OnVideoClickListener): ListAdapter<MovieVideoResults, VideosAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.create(LayoutInflater.from(parent.context), parent, onVideoClickListener)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))


    class ViewHolder(private val binding: ItemVideoBinding, onVideoClickListener: OnVideoClickListener): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onVideoClickListener.onVideoClick(binding.video!!)
            }
        }

        companion object {
            fun create(inflater: LayoutInflater, parent: ViewGroup, onVideoClickListener: OnVideoClickListener): ViewHolder {
                val itemVideoBinding: ItemVideoBinding = ItemVideoBinding.inflate(inflater, parent, false)
                return ViewHolder(itemVideoBinding, onVideoClickListener)
            }
        }

        fun bind(videoResults: MovieVideoResults) {
            binding.video = videoResults
            binding.executePendingBindings()
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieVideoResults>() {
            override fun areItemsTheSame(oldItem: MovieVideoResults, newItem: MovieVideoResults): Boolean =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: MovieVideoResults, newItem: MovieVideoResults): Boolean =
                oldItem.name == newItem.name
        }
    }

    interface OnVideoClickListener {
        fun onVideoClick(videos: MovieVideoResults)
    }
}