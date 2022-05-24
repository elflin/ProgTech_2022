package com.uc.firstappsprogtech

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.uc.firstappsprogtech.databinding.FragmentMenu2Binding
import com.uc.firstappsprogtech.databinding.FragmentMenu4Binding

class Menu4Fragment : Fragment() {

    private lateinit var viewBind: FragmentMenu4Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBind = FragmentMenu4Binding.inflate(layoutInflater, container, false)

        viewBind.title.text = "Menu terakhir di menu 4. Bisa coba klik button!"
        viewBind.testbutton.setOnClickListener{
            Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show()
            startActivity(Intent(context, RecyclerviewActivity::class.java))
        }

        return viewBind.root
    }

}