package com.uc.firstappsprogtech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uc.firstappsprogtech.databinding.FragmentBookBinding

class BookFragment : Fragment() {

    private lateinit var viewBind: FragmentBookBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBind = FragmentBookBinding.inflate(inflater, container, false)

        viewBind.titleBook.text = "This is BOOK FRAGMENT"

        return viewBind.root
    }
}