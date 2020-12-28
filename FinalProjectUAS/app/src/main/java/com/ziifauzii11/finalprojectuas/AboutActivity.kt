package com.ziifauzii11.finalprojectuas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Tentang"

            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        var fauzi = intent
        val namafauzi = fauzi.getStringExtra("NamaFauzi")
        val nimfauzi = fauzi.getStringExtra("NIMFauzi")
        val kelasfauzi = fauzi.getStringExtra("KelasFauzi")

        val nama_fauzi = findViewById<TextView>(R.id.nama_fauzi)
        val nim_fauzi = findViewById<TextView>(R.id.nim_fauzi)
        val kelas_fauzi = findViewById<TextView>(R.id.kelas_fauzi)

        var salsa = intent
        val namasalsa = salsa.getStringExtra("NamaSalsa")
        val nimsalsa = salsa.getStringExtra("NIMSalsa")
        val kelassalsa = salsa.getStringExtra("KelasSalsa")

        val nama_salsa = findViewById<TextView>(R.id.nama_salsa)
        val nim_salsa = findViewById<TextView>(R.id.nim_salsa)
        val kelas_salsa = findViewById<TextView>(R.id.kelas_salsa)

        nama_fauzi.text = namafauzi
        nim_fauzi.text = nimfauzi
        kelas_fauzi.text = kelasfauzi

        nama_salsa.text = namasalsa
        nim_salsa.text = nimsalsa
        kelas_salsa.text = kelassalsa

        img_fauzi.setOnClickListener {
            Toast.makeText(this, "Foto Fauzi", Toast.LENGTH_SHORT).show()

            img_salsa.setOnClickListener {
                Toast.makeText(this, "Foto Salsa", Toast.LENGTH_SHORT).show()
            }
        }
    }

        override fun onSupportNavigateUp(): Boolean {
            onBackPressed()
            return true
        }
    }