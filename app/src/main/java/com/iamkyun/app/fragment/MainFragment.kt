package com.iamkyun.app.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.iamkyun.app.R
import com.iamkyun.app.adapter.MainCarouselAdapter

class MainFragment : Fragment() {
    private lateinit var mainCarousel: ViewPager
    private val carouselImages = arrayOf(
        R.mipmap.header_pic_ad1,
        R.mipmap.header_pic_ad2,
        R.mipmap.header_pic_ad1,
        R.mipmap.header_pic_ad2
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainCarousel = view!!.findViewById(R.id.main_carousel)
        mainCarousel.adapter =
            activity?.let { MainCarouselAdapter(it, getCarouselImages(it, carouselImages)) }
    }

    private fun getCarouselImages(context: Context, imageResIds: Array<Int>): List<ImageView> {
        return imageResIds.map {
            val imageView = ImageView(context)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setImageResource(it)
            imageView
        }

    }
}