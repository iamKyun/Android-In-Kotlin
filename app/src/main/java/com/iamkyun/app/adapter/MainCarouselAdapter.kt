package com.iamkyun.app.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter

class MainCarouselAdapter(private val context: Context, private val images: List<ImageView>?) :
    PagerAdapter() {

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return images?.size ?: 0
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = images!![position]
        container.addView(imageView)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(images!![position])
    }
}