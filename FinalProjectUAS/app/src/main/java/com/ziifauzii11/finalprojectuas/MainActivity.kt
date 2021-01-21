package com.ziifauzii11.finalprojectuas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ziifauzii11.finalprojectuas.laptop.LaptopActivity
import com.ziifauzii11.finalprojectuas.phone.PhoneActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        phone.setOnClickListener{
            val intent = Intent(this, PhoneActivity::class.java)
            startActivity(intent)
        }
        laptop.setOnClickListener{
            val intent = Intent(this, LaptopActivity::class.java)
            startActivity(intent)
        }
        about.setOnClickListener{
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
        tentang.setOnClickListener{
            val intent = Intent(this, TentangActivity::class.java)
            startActivity(intent)
        }
    }
}