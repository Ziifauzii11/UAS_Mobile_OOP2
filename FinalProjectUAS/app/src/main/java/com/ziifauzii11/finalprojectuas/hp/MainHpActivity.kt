package com.ziifauzii11.finalprojectuas.hp

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

import kotlinx.android.synthetic.main.activity_main_hp.*
import kotlinx.android.synthetic.main.content_hp.*

class MainHpActivity : AppCompatActivity() {
    private var listHP = ArrayList<HP>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_hp)
        setSupportActionBar(toolbar as Toolbar?)

        fab.setOnClickListener { view ->
            var intent = Intent(this, HPActivity::class.java)
            startActivity(intent)
        }

        loadData()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        var dbAdapter = HPAdapter(this)
        var cursor = dbAdapter.allQuery()

        listHP.clear()
        if (cursor.moveToFirst()){
            do {
                val id = cursor.getInt(cursor.getColumnIndex("Id"))
                val merk = cursor.getString(cursor.getColumnIndex("Merk"))
                val tipe = cursor.getString(cursor.getColumnIndex("Tipe"))
                val harga = cursor.getString(cursor.getColumnIndex("Harga"))

                listHP.add(HP(id, merk, tipe, harga))
            }while (cursor.moveToNext())
        }

        var hpAdapter = HPAdapter(this, listHP)
        lvHp.adapter = hpAdapter
    }

    inner class HPAdapter: BaseAdapter{

        private var hpList = ArrayList<HP>()
        private var context: Context? = null

        constructor(context: Context, hpList: ArrayList<HP>) : super(){
            this.hpList = hpList
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

            var mHP = hpList[position]

            vh.tvMerk.text = mHP.merk
            vh.tvTipe.text = mHP.tipe
            vh.tvHarga.text = "Rp." + mHP.harga

            lvHp.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
                updateHP(mHP)
            }

            return view
        }

        override fun getItem(position: Int): Any {
            return hpList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return hpList.size
        }

    }

    private fun updateHP(hp: HP) {
        var  intent = Intent(this, HPActivity::class.java)
        intent.putExtra("MainActId", hp.id)
        intent.putExtra("MainActMerk", hp.merk)
        intent.putExtra("MainActTipe", hp.tipe)
        intent.putExtra("MainActHarga", hp.harga)
        startActivity(intent)
    }

    private class ViewHolder(view: View?){
        val tvMerk: TextView
        val tvTipe: TextView
        val tvHarga: TextView

        init {
            this.tvMerk = view?.findViewById(R.id.tvMerk) as TextView
            this.tvTipe = view?.findViewById(R.id.tvSeri) as TextView
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