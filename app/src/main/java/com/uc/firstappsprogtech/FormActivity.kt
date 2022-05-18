package com.uc.firstappsprogtech

import Model.User
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.uc.firstappsprogtech.databinding.ActivityFormBinding

class FormActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivityFormBinding
    private lateinit var user:User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityFormBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        Listener()
    }

    private fun Listener(){
        viewBind.DisplayButton.setOnClickListener {
            var nama = viewBind.NamaTextInputLayout.editText?.text.toString().trim()
            var alamat = viewBind.AlamatTextInputLayout.editText?.text.toString().trim()
            var no_telp = viewBind.TeleponTextInputLayout.editText?.text.toString().trim()
            var email = viewBind.EmailTextInputLayout.editText?.text.toString().trim()
            var password = viewBind.PasswordTextInputLayout.editText?.text.toString().trim()

            user = User(nama, alamat, no_telp, email, password)

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
            val myIntent = Intent(this, ResultActivity::class.java).apply {
                putExtra("DataUser", user)
                putExtra("Display", "Yes suksess")
            }
            startActivity(myIntent)
        }
    }


}