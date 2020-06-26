package com.iamkyun.app.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.iamkyun.app.R
import com.iamkyun.app.activity.LoginActivity

open class MeFragment : Fragment() {
    private lateinit var loginButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_me, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loginButton = view!!.findViewById(R.id.btn_login)
        loginButton.setOnClickListener {
            startActivity(Intent(activity, LoginActivity::class.java))
        }
    }

    companion object {
        private const val TAG = "MeFragment"
    }

}
