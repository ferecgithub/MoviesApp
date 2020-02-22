package com.ferechamitbeyli.moviesapp.model.reviews

import com.google.gson.annotations.SerializedName

data class MovieReviewResults(
    @SerializedName("id")
    var id: String,

    @SerializedName("author")
    var author: String,

    @SerializedName("content")
    var content: String
)