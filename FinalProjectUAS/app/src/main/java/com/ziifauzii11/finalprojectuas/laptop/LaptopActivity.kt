package com.ziifauzii11.finalprojectuas.laptop

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.*
import com.ziifauzii11.finalprojectuas.R
import kotlinx.android.synthetic.main.activity_laptop.*

class LaptopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laptop)

        val database = FirebaseDatabase.getInstance()

        var  myRef : DatabaseReference? = database.getReference("datalaptop")

        // Read Data
        myRef?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                // looping ketika mengambil data
                // merah ? coba tambahkan ()
                val dataArray = ArrayList<Laptop>()
                for (i in dataSnapshot.children){
                    val data = i.getValue(Laptop::class.java)
                    data?.key = i.key
                    data?.let { dataArray.add(it) }
                }
                rvListLaptop.adapter = LaptopAdapter(dataArray, object : LaptopAdapter.OnClick {
                    override fun edit(laptop: Laptop?) {
                        val intent = Intent(this@LaptopActivity, FormLaptopActivity::class.java)
                        intent.putExtra("laptop", laptop)
                        startActivity(intent)
                    }

                    override fun delete(key: String?) {
                        AlertDialog.Builder(this@LaptopActivity).apply {
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

        btAddLaptop.setOnClickListener {
            startActivity(Intent(this@LaptopActivity, FormLaptopActivity::class.java))
        }
    }
}