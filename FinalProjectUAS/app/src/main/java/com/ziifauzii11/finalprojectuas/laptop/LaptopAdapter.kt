package com.ziifauzii11.finalprojectuas.laptop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ziifauzii11.finalprojectuas.R
import kotlinx.android.synthetic.main.item_laptop.view.*

class LaptopAdapter(val datalaptop : ArrayList<Laptop>, val onClick : OnClick) : RecyclerView.Adapter<LaptopAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_laptop, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int = datalaptop.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(datalaptop.get(position))
        holder.itemView.btDeleteLaptop.setOnClickListener {
            onClick.delete(datalaptop.get(position).key)
        }
        holder.itemView.setOnClickListener {
            onClick.edit(datalaptop.get(position))
        }
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(laptop : Laptop){
            itemView.apply {
                val merk = "Brand       : ${laptop.merk}"
                val harga = "Harga       : Rp. ${laptop.harga}"
                val spesifikasi = "Spesifikasi : ${laptop.spesifikasi}"

                tvMerk.text = merk
                tvHarga.text = harga
                tvSpesifikasi.text = spesifikasi
            }
        }
    }


    interface OnClick {
        fun delete(key : String?)
        fun edit(laptop : Laptop?)
    }
}