package com.android.newsfeed.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Row(
    @SerializedName("description")
    val description: String? = "Description",
    @SerializedName("imageHref")
    val imageHref: String? = "https://via.placeholder.com/600x400",
    @SerializedName("title")
    val title: String? = "Title"
): Serializable