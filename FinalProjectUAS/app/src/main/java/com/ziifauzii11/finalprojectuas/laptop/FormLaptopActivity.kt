package com.ziifauzii11.finalprojectuas.laptop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import com.ziifauzii11.finalprojectuas.R
import kotlinx.android.synthetic.main.activity_form_laptop.*
import java.util.HashMap

class FormLaptopActivity : AppCompatActivity() {
    var laptop : Laptop? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_laptop)

        val data = intent.getSerializableExtra("laptop")
        var edit = true

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("datalaptop")

        if (data != null) {
            laptop = data as Laptop
            etMerkEdit.setText(laptop?.merk)
            etHargaEdit.setText(laptop?.harga)
            etSpesifikasiEdit.setText(laptop?.spesifikasi)

            btActFormLaptop.setText("Edit")
        } else {
            btActFormLaptop.setText("Tambah")
            edit = false
        }

        btActFormLaptop.setOnClickListener {
            if (edit) {
                val changeData = HashMap<String, Any>()
                changeData.put("merk", etMerkEdit.text.toString())
                changeData.put("harga", etHargaEdit.text.toString())
                changeData.put("spesifikasi", etSpesifikasiEdit.text.toString())

                myRef.child(laptop?.key.toString()).updateChildren(changeData)
                finish()
                startActivity(Intent(this, LaptopActivity::class.java))
            } else {
                val key = myRef.push().key

                val newLaptop = Laptop()
                newLaptop.merk = etMerkEdit.text.toString()
                newLaptop.harga = etHargaEdit.text.toString()
                newLaptop.spesifikasi = etSpesifikasiEdit.text.toString()

                myRef.child(key.toString()).setValue(newLaptop)
                finish()
                startActivity(Intent(this, LaptopActivity::class.java))
            }
        }
    }
}