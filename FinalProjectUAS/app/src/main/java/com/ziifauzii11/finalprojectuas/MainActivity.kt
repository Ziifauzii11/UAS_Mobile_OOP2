package com.ziifauzii11.finalprojectuas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ziifauzii11.finalprojectuas.hp.MainHpActivity
import com.ziifauzii11.finalprojectuas.laptop.MainLaptopActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_hp.setOnClickListener {
            val hp = Intent(this, MainHpActivity::class.java)
            startActivity(hp)
        }

        btn_laptop.setOnClickListener {
            val laptop = Intent(this, MainLaptopActivity::class.java)
            startActivity(laptop)
        }
    }
}