package com.uc.firstappsprogtech

import Adapter.ListDataRVAdapter
import Model.User
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.uc.firstappsprogtech.databinding.ActivityRecyclerviewBinding

class RecyclerviewActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivityRecyclerviewBinding

    private val listDataUser = ArrayList<User>()
    private val adapter = ListDataRVAdapter(listDataUser)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityRecyclerviewBinding.inflate(layoutInflater)
        setContentView(viewBind.root)

        setupRecyclerView()
        addDummyData()
    }

    private fun setupRecyclerView(){
        val layoutManager = LinearLayoutManager(baseContext)
        viewBind.listDataRV.layoutManager = layoutManager   // Set layout
        viewBind.listDataRV.adapter = adapter   // Set adapter
    }

    private fun addDummyData(){
        listDataUser.add(User("Marcel1", "Citraland1", "08112345", "marcel@gmail.com", "Asdf1234"))
        listDataUser.add(User("Marcel2", "Citraland1", "08112345", "marcel@gmail.com", "Asdf1234"))
        listDataUser.add(User("Marcel3", "Citraland1", "08112345", "marcel@gmail.com", "Asdf1234"))
        listDataUser.add(User("Marcel4", "Citraland1", "08112345", "marcel@gmail.com", "Asdf1234"))
        listDataUser.add(User("Marcel5", "Citraland1", "08112345", "marcel@gmail.com", "Asdf1234"))
        listDataUser.add(User("Marcel6", "Citraland1", "08112345", "marcel@gmail.com", "Asdf1234"))
        listDataUser.add(User("Marcel7", "Citraland1", "08112345", "marcel@gmail.com", "Asdf1234"))
        listDataUser.add(User("Marcel8", "Citraland1", "08112345", "marcel@gmail.com", "Asdf1234"))
        listDataUser.add(User("Marcel9", "Citraland1", "08112345", "marcel@gmail.com", "Asdf1234"))
        listDataUser.add(User("Marcel10", "Citraland1", "08112345", "marcel@gmail.com", "Asdf1234"))

        adapter.notifyDataSetChanged()
    }
}