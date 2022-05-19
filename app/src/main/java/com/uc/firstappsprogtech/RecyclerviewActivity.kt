package com.uc.firstappsprogtech

import Adapter.ListDataRVAdapter
import Database.GlobalVar
import Interface.CardListener
import Model.User
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.uc.firstappsprogtech.databinding.ActivityRecyclerviewBinding

class RecyclerviewActivity : AppCompatActivity(), CardListener {

    private lateinit var viewBind: ActivityRecyclerviewBinding
    private val adapter = ListDataRVAdapter(GlobalVar.listDataUser, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityRecyclerviewBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        CheckPermissions()
        setupRecyclerView()
        addDummyData()
        listener()
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
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

    private fun addDummyData(){
        GlobalVar.listDataUser.add(User("Marcel1", "Citraland1", "08112345", "marcel@gmail.com", "Asdf1234"))
        GlobalVar.listDataUser.add(User("Marcel2", "Citraland1", "08112345", "marcel@gmail.com", "Asdf1234"))
        GlobalVar.listDataUser.add(User("Marcel3", "Citraland1", "08112345", "marcel@gmail.com", "Asdf1234"))
        GlobalVar.listDataUser.add(User("Marcel4", "Citraland1", "08112345", "marcel@gmail.com", "Asdf1234"))
        GlobalVar.listDataUser.add(User("Marcel5", "Citraland1", "08112345", "marcel@gmail.com", "Asdf1234"))
        GlobalVar.listDataUser.add(User("Marcel6", "Citraland1", "08112345", "marcel@gmail.com", "Asdf1234"))
        GlobalVar.listDataUser.add(User("Marcel7", "Citraland1", "08112345", "marcel@gmail.com", "Asdf1234"))
        GlobalVar.listDataUser.add(User("Marcel8", "Citraland1", "08112345", "marcel@gmail.com", "Asdf1234"))
        GlobalVar.listDataUser.add(User("Marcel9", "Citraland1", "08112345", "marcel@gmail.com", "Asdf1234"))
        GlobalVar.listDataUser.add(User("Marcel10", "Citraland1", "08112345", "marcel@gmail.com", "Asdf1234"))

        adapter.notifyDataSetChanged()
    }

    override fun onCardClick(position: Int) {
        val myIntent = Intent(this, ResultActivity::class.java).apply {
            putExtra("position", position)
        }
        startActivity(myIntent)
    }
}