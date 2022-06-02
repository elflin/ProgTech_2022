package com.uc.firstappsprogtech

import Database.GlobalVar
import Database.VolleySingleton
import Model.User
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.uc.firstappsprogtech.databinding.ActivityResultBinding
import org.json.JSONObject


class ResultActivity : AppCompatActivity() {

    private lateinit var viewBind:ActivityResultBinding
    private var position = -1
    private lateinit var user:User

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
            val myIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }

        viewBind.pictureFab.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }

        viewBind.DeleteButton.setOnClickListener{
            DeleteData()
            finish()
        }

        viewBind.EditButton.setOnClickListener{
            val myIntent = Intent(this, FormActivity::class.java).apply {
                putExtra("position", position)
            }

            startActivity(myIntent)
        }
    }

    private fun DeleteData() {
        val jObj = JSONObject()
        jObj.put("id", position)

        val request = JsonObjectRequest(
            Request.Method.POST,
            GlobalVar.DeleteUser,
            jObj,
            {

            },
            {
                Toast.makeText(this, "Network Error", Toast.LENGTH_LONG).show()
                it.printStackTrace()
            }
        )

        VolleySingleton.getInstance(this).addToRequestQueue(request)
    }

    private fun GetIntent(){
        position = intent.getIntExtra("position", -1)
    }

    override fun onResume() {
        super.onResume()
        ReadFromDB()
    }

    private fun ReadFromDB() {
        val jObj = JSONObject()
        jObj.put("id", position)

        val request = JsonObjectRequest(
            Request.Method.POST,
            GlobalVar.ReadByID,
            jObj,
            {
                val jsonObj = it.getJSONObject("data")

                user = User(
                    jsonObj.getInt("id"),
                    jsonObj.getString("nama"),
                    jsonObj.getString("alamat"),
                    jsonObj.getString("no_telp"),
                    jsonObj.getString("email"),
                    jsonObj.getString("password"),
                    jsonObj.getString("imageString")
                )

                Display()
            },
            {
                Toast.makeText(this, "Network Error", Toast.LENGTH_LONG).show()
                it.printStackTrace()
            }
        )

        VolleySingleton.getInstance(this).addToRequestQueue(request)
    }

    private fun Display(){
        viewBind.NamaTextView.text = user.nama
        viewBind.AlamatTextView.text = user.alamat
        viewBind.NoTelpTextView.text = user.no_telp
        viewBind.EmailTextView.text = user.email
        viewBind.PasswordTextView.text = user.password

    }

}