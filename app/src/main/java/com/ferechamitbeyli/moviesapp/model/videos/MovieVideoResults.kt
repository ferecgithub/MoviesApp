package com.ferechamitbeyli.moviesapp.model.videos

import com.google.gson.annotations.SerializedName

data class MovieVideoResults(
    @SerializedName("id")
    var id: String,

    @SerializedName("key")
    var key: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("site")
    var site: String
)