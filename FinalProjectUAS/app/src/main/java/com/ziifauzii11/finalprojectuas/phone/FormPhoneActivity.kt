package com.ziifauzii11.finalprojectuas.phone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import com.ziifauzii11.finalprojectuas.R
import kotlinx.android.synthetic.main.activity_form_phone.*
import java.util.HashMap

class FormPhoneActivity : AppCompatActivity() {
    var phone : Phone? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_phone)

        val data = intent.getSerializableExtra("phone")
        var edit = true

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("dataphone")

        if (data != null) {
            phone = data as Phone
            etPhoneEdit.setText(phone?.merkPhone)
            etHargaPhoneEdit.setText(phone?.hargaPhone)
            etSpesifikasiPhoneEdit.setText(phone?.spesifikasiPhone)

            btActFormPhone.setText("Edit")
        } else {
            btActFormPhone.setText("Tambah")
            edit = false
        }

        btActFormPhone.setOnClickListener {
            if (edit) {
                val changeData = HashMap<String, Any>()
                changeData.put("merkPhone", etPhoneEdit.text.toString())
                changeData.put("hargaPhone", etHargaPhoneEdit.text.toString())
                changeData.put("spesifikasiPhone", etSpesifikasiPhoneEdit.text.toString())

                myRef.child(phone?.key.toString()).updateChildren(changeData)
                finish()
                startActivity(Intent(this, PhoneActivity::class.java))
            } else {
                val key = myRef.push().key

                val newPhone = Phone()
                newPhone.merkPhone = etPhoneEdit.text.toString()
                newPhone.hargaPhone = etHargaPhoneEdit.text.toString()
                newPhone.spesifikasiPhone = etSpesifikasiPhoneEdit.text.toString()

                myRef.child(key.toString()).setValue(newPhone)
                finish()
                startActivity(Intent(this, PhoneActivity::class.java))
            }
        }
    }
}