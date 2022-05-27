package com.uc.firstappsprogtech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uc.firstappsprogtech.databinding.FragmentBookBinding
import com.uc.firstappsprogtech.databinding.FragmentMailBinding

class MailFragment : Fragment() {
    private lateinit var viewBind: FragmentMailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBind = FragmentMailBinding.inflate(inflater, container, false)

        viewBind.titleMail.text = "This is MAIL FRAGMENT"

        return viewBind.root
    }
}