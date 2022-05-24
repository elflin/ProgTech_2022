package com.uc.firstappsprogtech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uc.firstappsprogtech.databinding.FragmentMenu1Binding
import com.uc.firstappsprogtech.databinding.FragmentMenu2Binding

class Menu2Fragment : Fragment() {

    private lateinit var viewBind: FragmentMenu2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBind = FragmentMenu2Binding.inflate(layoutInflater, container, false)

        viewBind.title.text = "Halo, lagi di menu 2 ya"

        return viewBind.root
    }

}