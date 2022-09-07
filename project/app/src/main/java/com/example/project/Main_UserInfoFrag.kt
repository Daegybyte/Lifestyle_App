package com.example.project

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class Main_UserInfoFrag : Fragment() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_userinfo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnEditProfile: Button = view.findViewById(R.id.btnEditProfile)

        btnEditProfile.setOnClickListener{
            val profileIntent = Intent(activity, ProfileActivity::class.java)
            startActivity(profileIntent)
        }
    }

}