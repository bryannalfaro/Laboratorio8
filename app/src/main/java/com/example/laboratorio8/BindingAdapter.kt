package com.example.laboratorio8


import android.widget.ImageView
import androidx.core.net.toUri

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Adapter for the URL image
 * @author Bryann Alfaro
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)
    }
}