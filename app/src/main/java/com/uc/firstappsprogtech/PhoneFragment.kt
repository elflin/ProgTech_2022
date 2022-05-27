package com.uc.firstappsprogtech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uc.firstappsprogtech.databinding.FragmentMailBinding
import com.uc.firstappsprogtech.databinding.FragmentPhoneBinding

class PhoneFragment : Fragment() {
    private lateinit var viewBind: FragmentPhoneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBind = FragmentPhoneBinding.inflate(inflater, container, false)

        viewBind.titlePhone.text = "This is PHONE FRAGMENT"

        return viewBind.root
    }
}