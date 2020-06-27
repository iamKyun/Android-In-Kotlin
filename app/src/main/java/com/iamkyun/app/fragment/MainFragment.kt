package com.iamkyun.app.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.iamkyun.app.R
import com.iamkyun.app.adapter.MainCarouselAdapter
import kotlin.properties.Delegates

class MainFragment : Fragment() {
    private lateinit var mainCarousel: ViewPager
    private lateinit var layoutPointGroup: LinearLayout
    private var carouselSize by Delegates.notNull<Int>()
    private var isStop = false

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
        val view = view!!
        mainCarousel = view.findViewById(R.id.main_carousel)
        val imageResIds = carouselImages
        mainCarousel.adapter =
            activity?.let { MainCarouselAdapter(it, getCarouselImages(it, imageResIds)) }
        carouselSize = imageResIds.size
        mainCarousel.addOnPageChangeListener(CarouselPageChangeListener(carouselSize))
        layoutPointGroup = view.findViewById(R.id.main_carousel_index)

        for (index in 1..carouselSize) {
            val v = ImageView(activity)
            val params = LinearLayout.LayoutParams(72, 26)
            params.leftMargin = 6
            params.rightMargin = 6
            v.layoutParams = params
            v.isEnabled = false
            v.setBackgroundResource(R.drawable.pointer_selector)
            layoutPointGroup.addView(v)
        }

        Thread {
            while (!isStop) {
                try {
                    Thread.sleep(3000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                //以下代码发送到主线程中执行
                activity?.runOnUiThread {
                    mainCarousel.currentItem = (mainCarousel.currentItem + 1) % carouselSize
                }
            }
        }.start()
    }

    override fun onDestroy() {
        isStop = true
        super.onDestroy()
    }

    inner class CarouselPageChangeListener(private var imageSize: Int = 0) :
        ViewPager.OnPageChangeListener {
        private var previousPoint: Int = 0

        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            val curPosition = position % imageSize
            layoutPointGroup.getChildAt(previousPoint).isEnabled = false;
            layoutPointGroup.getChildAt(curPosition).isEnabled = true;
            previousPoint = curPosition;
        }

        override fun onPageSelected(position: Int) {
        }
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