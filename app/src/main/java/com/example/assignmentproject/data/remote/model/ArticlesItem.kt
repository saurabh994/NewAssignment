package com.example.assignmentproject.data.remote.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@Entity
data class ArticlesItem(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    @SerializedName("publishedAt")
    var publishedAt: String? = "",
    @SerializedName("author")
    val author: String? = "",
    @SerializedName("urlToImage")
    val urlToImage: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("source")
    val source: Source? =  null,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("url")
    val url: String? = "",
    @SerializedName("content")
    val content: String? = ""
):Serializable{
        fun getDate() : String?{
            val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",Locale.getDefault())
            val date: Date? = dateFormat.parse(publishedAt?:"")

            val formatter: DateFormat =
                SimpleDateFormat("yyyy-MM-dd",Locale.getDefault())

            return date?.let { formatter.format(it) }
        }
}