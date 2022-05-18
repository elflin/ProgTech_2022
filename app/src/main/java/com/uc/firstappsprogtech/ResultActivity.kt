package com.uc.firstappsprogtech

import Model.User
import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.uc.firstappsprogtech.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var viewBind:ActivityResultBinding

    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){   // APLIKASI GALLERY SUKSES MENDAPATKAN IMAGE
            val uri = it.data?.data                 // GET PATH TO IMAGE FROM GALLEY
            viewBind.pictureImageview.setImageURI(uri)  // MENAMPILKAN DI IMAGE VIEW
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityResultBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        GetIntent()
        Listener()
    }

    private fun Listener(){
        viewBind.pictureImageview.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_PICK)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }

        viewBind.pictureFab.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_PICK)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }
    }

    private fun GetIntent(){
        val user  = intent.getParcelableExtra<User>("DataUser")
        if (user != null) {
            Display(user)
        }
    }


    private fun Display(user:User){
        viewBind.NamaTextView.text = user.nama
        viewBind.AlamatTextView.text = user.alamat
        viewBind.NoTelpTextView.text = user.no_telp
        viewBind.EmailTextView.text = user.email
        viewBind.PasswordTextView.text = user.password
    }

}