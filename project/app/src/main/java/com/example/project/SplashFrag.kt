package com.example.project

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


class SplashFrag : Fragment() {

    private lateinit var logoAnimation : AnimationDrawable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        val logoImage : ImageView = view.findViewById<ImageView>(R.id.logo_image).apply {
            setBackgroundResource(R.drawable.logo_animation)
            logoAnimation = background as AnimationDrawable
        }

        logoAnimation.start()

        return view
    }

}