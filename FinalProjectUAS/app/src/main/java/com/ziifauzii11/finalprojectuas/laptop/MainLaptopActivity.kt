package com.ziifauzii11.finalprojectuas.laptop

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.ziifauzii11.finalprojectuas.R
import kotlinx.android.synthetic.main.activity_main_laptop.*

import kotlinx.android.synthetic.main.activity_main_laptop.fab
import kotlinx.android.synthetic.main.activity_main_laptop.toolbar
import kotlinx.android.synthetic.main.content_laptop.*

class MainLaptopActivity : AppCompatActivity() {
    private var listLaptop = ArrayList<Laptop>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_laptop)
        setSupportActionBar(toolbar as Toolbar?)

        fab.setOnClickListener { view ->
            var intent = Intent(this, LaptopActivity::class.java)
            startActivity(intent)
        }

        loadData()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        var dbAdapter = LaptopAdapter(this)
        var cursor = dbAdapter.allQuery()

        listLaptop.clear()
        if (cursor.moveToFirst()){
            do {
                val id = cursor.getInt(cursor.getColumnIndex("Id"))
                val merk = cursor.getString(cursor.getColumnIndex("Merk"))
                val seri = cursor.getString(cursor.getColumnIndex("Seri"))
                val harga = cursor.getString(cursor.getColumnIndex("Harga"))

                listLaptop.add(Laptop(id, merk, seri, harga))
            }while (cursor.moveToNext())
        }

        var laptopAdapter = LaptopAdapter(this, listLaptop)
        lvLaptop.adapter = laptopAdapter
    }

    inner class LaptopAdapter: BaseAdapter{

        private var laptopList = ArrayList<Laptop>()
        private var context: Context? = null

        constructor(context: Context, laptopList: ArrayList<Laptop>) : super(){
            this.laptopList = laptopList
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            val view: View?
            val vh: ViewHolder

            if (convertView == null){
                view = layoutInflater.inflate(R.layout.hp, parent, false)
                vh = ViewHolder(view)
                view.tag = vh
                Log.i("db", "set tag for ViewHolder, position: " + position)
            }else{
                view = convertView
                vh = view.tag as ViewHolder
            }

            var mLaptop = laptopList[position]

            vh.tvMerk.text = mLaptop.merk
            vh.tvSeri.text = mLaptop.seri
            vh.tvHarga.text = "Rp." + mLaptop.harga

            lvLaptop.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
                updateHP(mLaptop)
            }

            return view
        }

        override fun getItem(position: Int): Any {
            return laptopList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return laptopList.size
        }

    }

    private fun updateHP(laptop: Laptop) {
        var  intent = Intent(this, LaptopActivity::class.java)
        intent.putExtra("MainActId", laptop.id)
        intent.putExtra("MainActMerk", laptop.merk)
        intent.putExtra("MainActSeri", laptop.seri)
        intent.putExtra("MainActHarga", laptop.harga)
        startActivity(intent)
    }

    private class ViewHolder(view: View?){
        val tvMerk: TextView
        val tvSeri: TextView
        val tvHarga: TextView

        init {
            this.tvMerk = view?.findViewById(R.id.tvMerk) as TextView
            this.tvSeri = view?.findViewById(R.id.tvSeri) as TextView
            this.tvHarga = view?.findViewById(R.id.tvHarga) as TextView
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}