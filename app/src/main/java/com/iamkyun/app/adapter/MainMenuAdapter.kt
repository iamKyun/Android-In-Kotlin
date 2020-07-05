package com.iamkyun.app.adapter

import android.content.Context
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.iamkyun.app.R
import com.iamkyun.app.entity.Menu

class MainMenuAdapter(private val context: Context, private val nemus: Array<Menu>) :
    RecyclerView.Adapter<MainMenuAdapter.MenuHolder>() {

    inner class MenuHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.menu_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.main_menu_item, parent, false)
        return MenuHolder(view)
    }

    override fun getItemCount(): Int {
        return nemus.size
    }

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        val menu = nemus[position]
        holder.textView.text = context.getString(menu.name)
        val icon = AppCompatResources.getDrawable(context, menu.icon)!!
        icon.bounds = Rect(0, 0, icon.minimumWidth, icon.minimumHeight)
        holder.textView.setCompoundDrawables(null, icon, null, null)
    }
}