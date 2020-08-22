package com.example.assignmentproject.common.extension

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


@BindingAdapter("app:background")
fun ConstraintLayout.setViewBackground(image: String) {
    val bitmap = getBitmapFromURL(image)
    val dr: Drawable = BitmapDrawable(bitmap)
    background = dr
}
fun getBitmapFromURL(imageUrl: String?): Bitmap? {
    return try {
        val url = URL(imageUrl)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
        connection.setDoInput(true)
        connection.connect()
        val input: InputStream = connection.getInputStream()
        BitmapFactory.decodeStream(input)
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}