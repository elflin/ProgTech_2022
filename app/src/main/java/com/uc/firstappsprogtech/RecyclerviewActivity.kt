package com.uc.firstappsprogtech

import Adapter.ListDataRVAdapter
import Database.GlobalVar
import Database.VolleySingleton
import Interface.CardListener
import Model.User
import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.uc.firstappsprogtech.databinding.ActivityRecyclerviewBinding
import org.json.JSONArray
import org.json.JSONObject

class RecyclerviewActivity : AppCompatActivity(), CardListener {

    private lateinit var viewBind: ActivityRecyclerviewBinding
    private val adapter = ListDataRVAdapter(GlobalVar.listDataUser, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityRecyclerviewBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        CheckPermissions()
        setupRecyclerView()
        listener()
    }

    override fun onResume() {
        super.onResume()
        GlobalVar.listDataUser.clear()
        loadFromDB()
    }

    private fun CheckPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), GlobalVar.STORAGE_PERMISSION_CODE)
        } else {
            Toast.makeText(this, "Storage Permission already granted", Toast.LENGTH_SHORT).show()
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), GlobalVar.STORAGE_PERMISSION_CODE)
        } else {
            Toast.makeText(this, "Storage Permission already granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == GlobalVar.STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun listener() {
        viewBind.addDataFAB.setOnClickListener{
            val myIntent = Intent(this, FormActivity::class.java)
            startActivity(myIntent)
        }
    }

    private fun setupRecyclerView(){
        val layoutManager = LinearLayoutManager(baseContext)
        viewBind.listDataRV.layoutManager = layoutManager   // Set layout
        viewBind.listDataRV.adapter = adapter   // Set adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadFromDB(){
        val request = JsonObjectRequest(Request.Method.GET, GlobalVar.ReadAllData, null,
            {
                val jsonArray = it.getJSONArray("data")
                for(i in 0 until jsonArray.length()){
                    val jsonObj = jsonArray.getJSONObject(i)
                    val user = User(
                        jsonObj.getString("nama"),
                        jsonObj.getString("alamat"),
                        jsonObj.getString("no_telp"),
                        jsonObj.getString("email"),
                        jsonObj.getString("password") ,
                        jsonObj.getInt("id"),
                        jsonObj.getString("pictureArray")
                    )
                    GlobalVar.listDataUser.add(user)
                }
                adapter.notifyDataSetChanged()
            }, {
                it.printStackTrace()
            }
        )

        VolleySingleton.getInstance(this).addToRequestQueue(request)
    }

    override fun onCardClick(position: Int) {
        val myIntent = Intent(this, ResultActivity::class.java).apply {
            putExtra("position", position)
        }
        startActivity(myIntent)
    }
}