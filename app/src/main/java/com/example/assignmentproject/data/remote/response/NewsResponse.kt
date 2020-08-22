package com.example.assignmentproject.data.remote.response

import com.example.assignmentproject.data.remote.model.ArticlesItem
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsResponse(
	@SerializedName("totalResults")
	val totalResults: Int? = null,
	@SerializedName("articles")
	val articles: List<ArticlesItem>
):Serializable

