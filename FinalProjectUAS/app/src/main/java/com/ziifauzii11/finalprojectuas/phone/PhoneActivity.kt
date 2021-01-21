package com.ziifauzii11.finalprojectuas.phone

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.*
import com.ziifauzii11.finalprojectuas.R
import kotlinx.android.synthetic.main.activity_phone.*

class PhoneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone)

        val database = FirebaseDatabase.getInstance()

        var  myRef : DatabaseReference? = database.getReference("dataphone")

        // Read Data
        myRef?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                // looping ketika mengambil data
                // merah ? coba tambahkan ()
                val dataArray = ArrayList<Phone>()
                for (i in dataSnapshot.children){
                    val data = i.getValue(Phone::class.java)
                    data?.key = i.key
                    data?.let { dataArray.add(it) }
                }
                rvListPhone.adapter = PhoneAdapter(dataArray, object : PhoneAdapter.OnClick {
                    override fun edit(phone: Phone?) {
                        val intent = Intent(this@PhoneActivity, FormPhoneActivity::class.java)
                        intent.putExtra("phone", phone)
                        startActivity(intent)
                    }

                    override fun delete(key: String?) {
                        AlertDialog.Builder(this@PhoneActivity).apply {
                            setTitle("Hapus ?")
                            setPositiveButton("Ya") { dialogInterface: DialogInterface, i: Int ->
                                myRef?.child(key.toString())?.removeValue()
//                                Toast.makeText(this@MainActivity, key, Toast.LENGTH_SHORT).show()
                            }
                            setNegativeButton("Tidak", { dialogInterface: DialogInterface, i: Int -> })
                        }.show()
                    }
                })
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException())
            }
        })

        btAddPhone.setOnClickListener {
            startActivity(Intent(this@PhoneActivity, FormPhoneActivity::class.java))
        }
    }
}