package com.iamkyun.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.iamkyun.app.R

class MainCarouselAdapter(private val context: Context, private val images: Array<Int>?) :
    RecyclerView.Adapter<MainCarouselAdapter.CarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        // 在此实例化View视图
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.carousel_page, parent, false)
        return CarouselViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images?.size ?: 0
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.imageView.setImageResource(images!![position])
    }

    class CarouselViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.carousel_page_iv)
    }

}