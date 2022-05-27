package com.uc.firstappsprogtech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.uc.firstappsprogtech.databinding.ActivityBotnavBinding

class BotnavActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivityBotnavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityBotnavBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        setCurrFragment(BookFragment())

        viewBind.bottomNavBotnav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.book_menu -> setCurrFragment(BookFragment())
                R.id.mail_menu -> setCurrFragment(MailFragment())
                R.id.phone_menu -> setCurrFragment(BookFragment())
                R.id.burger_menu -> setCurrFragment(BurgerFragment())
            }
            true
        }
    }

    private fun setCurrFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(viewBind.frameLayoutBotnav.id, fragment)
            commit()
        }
    }
}