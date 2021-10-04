package com.phaedra.cleanarchitecturesample.domain.model

import com.google.gson.annotations.SerializedName

data class Post(
    var body: String,
    var id: Int,
    var title: String,
)