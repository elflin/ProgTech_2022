package com.uc.firstappsprogtech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uc.firstappsprogtech.databinding.FragmentBookBinding
import com.uc.firstappsprogtech.databinding.FragmentBurgerBinding

class BurgerFragment : Fragment() {
    private lateinit var viewBind: FragmentBurgerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBind = FragmentBurgerBinding.inflate(inflater, container, false)

        viewBind.titleBurger.text = "This is BURGER FRAGMENT"

        return viewBind.root
    }
}