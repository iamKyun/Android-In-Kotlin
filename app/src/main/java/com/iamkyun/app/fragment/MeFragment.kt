package com.iamkyun.app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.iamkyun.app.R

class MeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_me, container, false)
        val meMenuSail = view.findViewById<TextView>(R.id.me_menu_sail)
        val meMenuYh = view.findViewById<TextView>(R.id.me_menu_yh)
        val meMenuGo = view.findViewById<TextView>(R.id.me_menu_go)
        val drawableSail = resources.getDrawable(R.mipmap.me_menu_sail, null)
        val drawableYh = resources.getDrawable(R.mipmap.me_menu_yh, null)
        val drawableGo = resources.getDrawable(R.mipmap.me_menu_go, null)
        drawableSail.setBounds(0, 0, 80, 80)
        drawableYh.setBounds(0, 0, 80, 80)
        drawableGo.setBounds(0, 0, 80, 80)
        meMenuSail.setCompoundDrawables(null, drawableSail, null, null)
        meMenuYh.setCompoundDrawables(null, drawableYh, null, null)
        meMenuGo.setCompoundDrawables(null, drawableGo, null, null)
        return view
    }
}