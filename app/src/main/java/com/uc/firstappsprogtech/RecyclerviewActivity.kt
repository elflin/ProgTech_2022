package com.uc.firstappsprogtech

import Adapter.ListDataRVAdapter
import Database.GlobalVar
import Interface.CardListener
import Model.User
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.uc.firstappsprogtech.databinding.ActivityRecyclerviewBinding

class RecyclerviewActivity : AppCompatActivity(), CardListener {

    private lateinit var viewBind: ActivityRecyclerviewBinding
    private val adapter = ListDataRVAdapter(GlobalVar.listDataUser, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityRecyclerviewBinding.inflate(layoutInflater)
        setContentView(viewBind.root)

        setupRecyclerView()
        addDummyData()
        listener()
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
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