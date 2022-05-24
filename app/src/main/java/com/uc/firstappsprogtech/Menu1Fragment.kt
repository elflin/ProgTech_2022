package com.uc.firstappsprogtech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.uc.firstappsprogtech.databinding.FragmentMenu1Binding

class Menu1Fragment : Fragment() {

    private lateinit var viewBind: FragmentMenu1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBind = FragmentMenu1Binding.inflate(layoutInflater, container, false)

        viewBind.titleMenu1.text = "Ini menu 1"

        return viewBind.root
    }

}