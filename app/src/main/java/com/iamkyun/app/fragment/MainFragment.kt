package com.iamkyun.app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.iamkyun.app.R
import com.iamkyun.app.adapter.MainCarouselAdapter
import kotlin.properties.Delegates

class MainFragment : Fragment() {
    private lateinit var mainCarousel: ViewPager2
    private lateinit var layoutPointGroup: LinearLayout
    private var carouselSize by Delegates.notNull<Int>()
    private var isStop = false

    private val carouselImages = arrayOf(
        R.mipmap.header_pic_ad2,
        R.mipmap.header_pic_ad1,
        R.mipmap.header_pic_ad2,
        R.mipmap.header_pic_ad1,
        R.mipmap.header_pic_ad2,
        R.mipmap.header_pic_ad1
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
            activity?.let { MainCarouselAdapter(it, imageResIds) }
        carouselSize = imageResIds.size
        mainCarousel.registerOnPageChangeCallback(CarouselPageChangeListener(carouselSize))
        layoutPointGroup = view.findViewById(R.id.main_carousel_index)
        mainCarousel.setCurrentItem(1, false)

        for (index in 1..carouselSize - 2) {
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
        ViewPager2.OnPageChangeCallback() {

        private var previousPoint: Int = 0
        private var currentPosition: Int = 0

        override fun onPageSelected(position: Int) {
            currentPosition = position;
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            if (currentPosition != 0 && currentPosition != imageSize - 1) {
                val curPosition = currentPosition + 1 % imageSize - 2
                layoutPointGroup.getChildAt(previousPoint).isEnabled = false
                layoutPointGroup.getChildAt(curPosition).isEnabled = true
                previousPoint = curPosition
            }
        }

        override fun onPageScrollStateChanged(state: Int) {
            if (state == ViewPager2.SCROLL_STATE_IDLE && (currentPosition == 0 || currentPosition == imageSize - 1)) {
                val newPos = if (currentPosition == 0) imageSize - 2 else 1
                mainCarousel.setCurrentItem(newPos, false)
            }
        }
    }

}