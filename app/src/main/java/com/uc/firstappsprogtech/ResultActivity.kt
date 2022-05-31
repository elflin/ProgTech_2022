package com.uc.firstappsprogtech

import Database.GlobalVar
import Database.VolleySingleton
import Model.User
import android.R.attr.bitmap
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.uc.firstappsprogtech.databinding.ActivityResultBinding
import org.json.JSONObject
import java.nio.ByteBuffer


class ResultActivity : AppCompatActivity() {

    private lateinit var viewBind:ActivityResultBinding
    private var position = -1
    private lateinit var user:User
    private var imageData: ByteArray? = null

    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){   // APLIKASI GALLERY SUKSES MENDAPATKAN IMAGE
            val uri = it.data?.data                 // GET PATH TO IMAGE FROM GALLEY
            if (uri != null) {
                createImageData(uri)
            }
        }
    }

    private fun createImageData(uri: Uri) {
        val inputStream = contentResolver.openInputStream(uri)
        inputStream?.buffered()?.use {
            imageData = it.readBytes()
            user.imageString = GlobalVar.ByteArrToString(imageData!!)
            updateDatatoDB()
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
            DeleteData()
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
    }

    override fun onResume() {
        super.onResume()
        getDataFromDB()
    }

    private fun Display(){
        viewBind.NamaTextView.text = user.nama
        viewBind.AlamatTextView.text = user.alamat
        viewBind.NoTelpTextView.text = user.no_telp
        viewBind.EmailTextView.text = user.email
        viewBind.PasswordTextView.text = user.password
        if(user.imageString != "null") {
            val bArray = GlobalVar.StringToByteArr(user.imageString)
            val options = BitmapFactory.Options()
            options.inSampleSize = 1
            options.inScaled = true
            val bitMap = BitmapFactory.decodeByteArray(
                bArray,
                0,
                bArray.size,
                options
            )
            viewBind.pictureImageview.setImageBitmap(bitMap)
        }
    }

    private fun updateDatatoDB(){
        val obj = JSONObject()
        obj.put("id", user.id)
        obj.put("nama", user.nama)
        obj.put("alamat", user.alamat)
        obj.put("no_telp", user.no_telp)
        obj.put("email", user.email)
        obj.put("password", user.password)
        obj.put("pictureArray", user.imageString)

        val request = JsonObjectRequest(Request.Method.POST, GlobalVar.UpdateData, obj,
            {
                val message = it.getString("Message")
                if (message == "Success"){
                    Toast.makeText(this, "Update Success", Toast.LENGTH_SHORT).show()
                    getDataFromDB()
                }else{
                    Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show()
                }
            },{
                it.printStackTrace()
            }
        )

        VolleySingleton.getInstance(this).addToRequestQueue(request)
    }

    private fun getDataFromDB(){
        val obj = JSONObject()
        obj.put("id", position)

        val request = JsonObjectRequest(Request.Method.POST, GlobalVar.ReadByIdData, obj,
            {
                val jObj = it.getJSONObject("data")
                user = User(
                    jObj.getString("nama"),
                    jObj.getString("alamat"),
                    jObj.getString("no_telp"),
                    jObj.getString("email"),
                    jObj.getString("password"),
                    jObj.getInt("id"),
                    jObj.getString("pictureArray")
                )
                Display()
            },{
                it.printStackTrace()
            }
        )

        VolleySingleton.getInstance(this).addToRequestQueue(request)
    }

    private fun DeleteData(){
        val obj = JSONObject()
        obj.put("id", position)

        val request = JsonObjectRequest(Request.Method.POST, GlobalVar.DeleteData, obj,
            {
                if(it.getString("message") == "Delete success"){
                    Toast.makeText(this, "Delete Success", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(this, "Delete Failed", Toast.LENGTH_SHORT).show()
                }
            },{
                it.printStackTrace()
            }
        )

        VolleySingleton.getInstance(this).addToRequestQueue(request)

    }

}