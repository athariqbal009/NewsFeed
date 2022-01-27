package com.android.newsfeed.data.model


import com.google.gson.annotations.SerializedName

data class FeedModel(
    @SerializedName("rows")
    val rows: List<Row>,
    @SerializedName("title")
    val title: String
)