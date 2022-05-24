package com.uc.firstappsprogtech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uc.firstappsprogtech.databinding.FragmentMenu2Binding
import com.uc.firstappsprogtech.databinding.FragmentMenu3Binding

class Menu3Fragment : Fragment() {

    private lateinit var viewBind: FragmentMenu3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBind = FragmentMenu3Binding.inflate(layoutInflater, container, false)

        viewBind.title.text = "Di menu 3, lanjut"

        return viewBind.root
    }

}