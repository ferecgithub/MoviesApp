package com.ferechamitbeyli.moviesapp.model.movie

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movies")
data class MovieResults(

    @PrimaryKey
    @SerializedName("id")
    var movieId: Int,

    @SerializedName("poster_path")
    var poster_path: String,

    @SerializedName("overview")
    var overview: String,

    @SerializedName("release_date")
    var release_date: String,

    @SerializedName("original_title")
    var original_title: String,

    @SerializedName("original_language")
    var original_language: String,

    @SerializedName("title")
    var title: String,

    @SerializedName("backdrop_path")
    var backdrop_path: String,

    @SerializedName("popularity")
    var popularity: Double,

    @SerializedName("vote_count")
    var vote_count: Int,

    @SerializedName("video")
    var video: Boolean,

    @SerializedName("vote_average")
    var vote_average: Double
): Parcelable