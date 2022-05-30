package com.uc.firstappsprogtech

import Database.GlobalVar
import Database.VolleySingleton
import Model.User
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.uc.firstappsprogtech.databinding.ActivityFormBinding
import org.json.JSONObject

class FormActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivityFormBinding
    private lateinit var user:User
    private var position = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityFormBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        GetIntent()
        Listener()
    }

    private fun GetIntent() {
        position = intent.getIntExtra("position", -1)
        Toast.makeText(this, position.toString(), Toast.LENGTH_LONG).show()
        if(position != -1){
            getDataFromDB()
        }
    }

    private fun Display(){
        viewBind.NamaTextInputLayout.editText?.setText(user.nama)
        viewBind.AlamatTextInputLayout.editText?.setText(user.alamat)
        viewBind.TeleponTextInputLayout.editText?.setText(user.no_telp)
        viewBind.EmailTextInputLayout.editText?.setText(user.email)
        viewBind.PasswordTextInputLayout.editText?.setText(user.password)
    }

    private fun Listener(){
        viewBind.DisplayButton.setOnClickListener {
            var nama = viewBind.NamaTextInputLayout.editText?.text.toString().trim()
            var alamat = viewBind.AlamatTextInputLayout.editText?.text.toString().trim()
            var no_telp = viewBind.TeleponTextInputLayout.editText?.text.toString().trim()
            var email = viewBind.EmailTextInputLayout.editText?.text.toString().trim()
            var password = viewBind.PasswordTextInputLayout.editText?.text.toString().trim()

            if(position == -1){
                user = User(nama, alamat, no_telp, email, password, -1, "")
            }else{
                user.nama = nama
                user.alamat = alamat
                user.no_telp = no_telp
                user.email = email
                user.password = password
            }
            checker()
        }
    }

    private fun checker(){
        var isCompleted:Boolean = true

        //nama
        if(user.nama!!.isEmpty()){
            viewBind.NamaTextInputLayout.error = "Tolong isi kolom nama"
            isCompleted = false
        }else{
            viewBind.NamaTextInputLayout.error = ""
        }

        //alamat
        if(user.alamat!!.isEmpty()){
            viewBind.AlamatTextInputLayout.error = "Tolong isi kolom alamat"
            isCompleted = false
        }else{
            viewBind.AlamatTextInputLayout.error = ""
        }

        //No telp
        if(user.no_telp!!.isEmpty()){
            viewBind.TeleponTextInputLayout.error = "Tolong isi kolom telepon"
            isCompleted = false
        }else{
            viewBind.TeleponTextInputLayout.error = ""
        }

        //Email
        if(user.email!!.isEmpty()){
            viewBind.EmailTextInputLayout.error = "Tolong isi kolom email"
            isCompleted = false
        }else{
            // Berguna untuk cek apakah input merupakan email
            if(!Patterns.EMAIL_ADDRESS.matcher(user.email).matches()){
                viewBind.EmailTextInputLayout.error = "Tolong masukan alamat email yang benar"
                isCompleted = false
            }else {
                viewBind.EmailTextInputLayout.error = ""
            }
        }

        // Password
        if (user.password!!.isEmpty()){
            viewBind.PasswordTextInputLayout.error = "Tolong isi kolom password"
            isCompleted = false
        }else{
            if (user.password!!.length < 8){
                viewBind.PasswordTextInputLayout.error = "Jumlah password min 8 karakter"
                isCompleted = false
            }else if(!user.password!!.matches(".*[a-z].*".toRegex())){
                viewBind.PasswordTextInputLayout.error = "Password tidak memiliki huruf kecil"
                isCompleted = false
            }else if(!user.password!!.matches(".*[A-Z].*".toRegex())){
                viewBind.PasswordTextInputLayout.error = "Password tidak memiliki huruf kapital"
                isCompleted = false
            }else{
                viewBind.PasswordTextInputLayout.error = ""
            }
        }

        if (isCompleted){
            if (position == -1){
                InsertData()
            }
            else{
                updateDatatoDB()
            }

        }
    }

    private fun InsertData(){
        val obj = JSONObject()
        obj.put("nama", user.nama)
        obj.put("alamat", user.alamat)
        obj.put("no_telp", user.no_telp)
        obj.put("email", user.email)
        obj.put("password", user.password)

        val request = JsonObjectRequest(
            Request.Method.POST, GlobalVar.InsertData, obj,
            {
                val message = it.getString("Message")
                if (message == "Success"){
                    Toast.makeText(this, "Insert Success", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(this, "Insert Failed", Toast.LENGTH_SHORT).show()
                }
            },{
                it.printStackTrace()
            }
        )

        VolleySingleton.getInstance(this).addToRequestQueue(request)
    }

    private fun updateDatatoDB(){
        val obj = JSONObject()
        obj.put("id", user.id)
        obj.put("nama", user.nama)
        obj.put("alamat", user.alamat)
        obj.put("no_telp", user.no_telp)
        obj.put("email", user.email)
        obj.put("password", user.password)
//        obj.put("pictureArray", user.imageArray)

        val request = JsonObjectRequest(
            Request.Method.POST, GlobalVar.UpdateData, obj,
            {
                val message = it.getString("Message")
                if (message == "Success"){
                    Toast.makeText(this, "Update Success", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show()
                }
            },{
                it.printStackTrace()
            }
        )

        VolleySingleton.getInstance(this).addToRequestQueue(request)
        finish()
    }

    private fun getDataFromDB() {
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
            }, {
                it.printStackTrace()
            }
        )

        VolleySingleton.getInstance(this).addToRequestQueue(request)

    }

}