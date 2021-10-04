package com.phaedra.cleanarchitecturesample.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.phaedra.cleanarchitecturesample.domain.model.Post

data class PostDto(
    @SerializedName("body")
    var body: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("userId")
    var userId: Int
)

fun PostDto.toPost():Post{
    return Post(
        body = body,
        id = id,
        title=title
    )
}