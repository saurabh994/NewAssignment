package com.example.assignmentproject.data.remote.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Source(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("id")
    val id: String? = null
):Serializable