package com.uc.firstappsprogtech

import Database.GlobalVar
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
    private var position = -1

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

        viewBind.DeleteButton.setOnClickListener{
            GlobalVar.listDataUser.removeAt(position)
            finish()
        }

        viewBind.EditButton.setOnClickListener{
            val myIntent = Intent(this, FormActivity::class.java).apply {
                putExtra("position", position)
            }

            startActivity(myIntent)
        }
    }

    private fun GetIntent(){
        position = intent.getIntExtra("position", -1)
        val user = GlobalVar.listDataUser[position]
        Display(user)
    }

    override fun onResume() {
        super.onResume()
        val user = GlobalVar.listDataUser[position]
        Display(user)
    }

    private fun Display(user:User){
        viewBind.NamaTextView.text = user.nama
        viewBind.AlamatTextView.text = user.alamat
        viewBind.NoTelpTextView.text = user.no_telp
        viewBind.EmailTextView.text = user.email
        viewBind.PasswordTextView.text = user.password
    }

}