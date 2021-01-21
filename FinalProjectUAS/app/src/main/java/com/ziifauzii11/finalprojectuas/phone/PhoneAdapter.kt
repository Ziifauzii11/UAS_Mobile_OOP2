package com.ziifauzii11.finalprojectuas.phone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ziifauzii11.finalprojectuas.R
import kotlinx.android.synthetic.main.item_phone.view.*

class PhoneAdapter(val dataphone : ArrayList<Phone>, val onClick : OnClick) : RecyclerView.Adapter<PhoneAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_phone, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int = dataphone.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(dataphone.get(position))
        holder.itemView.btDeletePhone.setOnClickListener {
            onClick.delete(dataphone.get(position).key)
        }
        holder.itemView.setOnClickListener {
            onClick.edit(dataphone.get(position))
        }
    }



    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(phone : Phone){
            itemView.apply {
                val merkPhone = "Brand       : ${phone.merkPhone}"
                val hargaPhone = "Harga       : Rp.  ${phone.hargaPhone}"
                val spesifikasiPhone = "Spesifikasi : ${phone.spesifikasiPhone}"

                tvPhoneName.text = merkPhone
                tvPhoneHarga.text = hargaPhone
                tvPhoneSpesifikasi.text = spesifikasiPhone
            }
        }
    }

    interface OnClick {
        fun delete(key : String?)
        fun edit(phone : Phone?)
    }
}