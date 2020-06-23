package com.iamkyun.app

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.iamkyun.app.fragment.FindFragment
import com.iamkyun.app.fragment.MainFragment
import com.iamkyun.app.fragment.MeFragment

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var navMain: TextView
    private lateinit var navFind: TextView
    private lateinit var navMe: TextView
    private val fragmentMap = mapOf(
        R.id.nav_main to MainFragment(),
        R.id.nav_find to FindFragment(),
        R.id.nav_me to MeFragment()
    )

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initView()
        val transaction = this.supportFragmentManager.beginTransaction()
        fragmentMap.forEach { (_, u) ->
            transaction.add(R.id.content, u)
        }
        transaction.commit()
        showFragment(R.id.nav_main)
    }

    private fun showFragment(id: Int) {
        val transaction = this.supportFragmentManager.beginTransaction()
        val fragment = fragmentMap.getValue(id)
        fragmentMap.forEach { (i, u) ->
            transaction.hide(u)
            this.findViewById<TextView>(i).isEnabled = true
        }
        transaction.show(fragment)
        transaction.commit()
        this.findViewById<TextView>(id).isEnabled = false
    }

    private fun initView() {
        navMain = this.findViewById(R.id.nav_main)
        navFind = this.findViewById(R.id.nav_find)
        navMe = this.findViewById(R.id.nav_me)
        navMain.setOnClickListener(this)
        navFind.setOnClickListener(this)
        navMe.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            showFragment(v.id)
        }

    }

}

