package com.ziifauzii11.finalprojectuas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ziifauzii11.finalprojectuas.hp.MainHpActivity
import com.ziifauzii11.finalprojectuas.laptop.MainLaptopActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        phone.setOnClickListener{
            Toast.makeText(applicationContext,"Data SmartPhone", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainHpActivity::class.java)
            startActivity(intent)
        }

        laptop.setOnClickListener{
            Toast.makeText(applicationContext,"Data Laptop", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainLaptopActivity::class.java)
            startActivity(intent)
        }

        tentang.setOnClickListener{
            Toast.makeText(applicationContext,"Tentang", Toast.LENGTH_SHORT).show()
            val namaFauzi = "Dwi Febi Fauzi"
            val nimFauzi = "18090125"
            val kelasFauzi = "5C"

            val namaSalsa = "Asri Nihal Salsabila"
            val nimSalsa = "18090087"
            val kelasSalsa = "5C"

            val tentang = Intent(this, AboutActivity::class.java)
            tentang.putExtra("NamaFauzi", namaFauzi)
            tentang.putExtra("NIMFauzi", nimFauzi)
            tentang.putExtra("KelasFauzi", kelasFauzi)

            tentang.putExtra("NamaSalsa", namaSalsa)
            tentang.putExtra("NIMSalsa", nimSalsa)
            tentang.putExtra("KelasSalsa", kelasSalsa)
            startActivity(tentang)
        }

        keluar.setOnClickListener{
            Toast.makeText(applicationContext,"Keluar", Toast.LENGTH_SHORT).show()
            finish();
        }
    }
}