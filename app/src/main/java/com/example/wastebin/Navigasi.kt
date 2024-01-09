package com.example.wastebin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Navigasi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigasi)
        var bottomnav = findViewById<BottomNavigationView>(R.id.nav_menu)
        bottomnav.setOnItemSelectedListener{
            when(it.itemId) {
                R.id.Homefrag->{
                    loadFragment(f_home())
                    true
                }
                R.id.Maps->{
                    loadFragment(MapsFragment())
                    true
                }
                R.id.History->{
                    loadFragment(f_history())
                    true
                }
                R.id.Akun->{
                    loadFragment(f_akun())
                    true
                }
                else ->{false}
            }

        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.navigationBarColor = resources.getColor(R.color.private_acc)
//        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.f_container,fragment)
        transaction.commit()
    }
    }