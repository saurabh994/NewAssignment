package com.example.assignmentproject.common.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.assignmentproject.di.module.GlideApp

@BindingAdapter(value = ["android:src"], requireAll = false)
fun ImageView.setImage(image: String?) {
    GlideApp.with(context)
        .load(image)
        .into(this)
}