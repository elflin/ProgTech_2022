package com.uc.firstappsprogtech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.uc.firstappsprogtech.databinding.ActivityBottomnavbarBinding

class BottomnavbarActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivityBottomnavbarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityBottomnavbarBinding.inflate(layoutInflater)
        setContentView(viewBind.root)

        SetCurrentFragment(Menu1Fragment())

        viewBind.bottomNavigationViewBottomnavbar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu1_menu -> SetCurrentFragment(Menu1Fragment())
                R.id.menu2_menu -> SetCurrentFragment(Menu2Fragment())
                R.id.menu3_menu -> SetCurrentFragment(Menu3Fragment())
                R.id.menu4_menu -> SetCurrentFragment(Menu4Fragment())
            }
            true
        }
    }

    private fun SetCurrentFragment(fragment: Fragment){
        this.supportFragmentManager.beginTransaction().apply {
            replace(viewBind.framelayoutBottomnavbar.id, fragment)
            commit()
        }
    }
}