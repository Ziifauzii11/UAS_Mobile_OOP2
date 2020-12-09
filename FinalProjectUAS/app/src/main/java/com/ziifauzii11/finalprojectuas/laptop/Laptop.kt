package com.ziifauzii11.finalprojectuas.laptop

class Laptop {
    var id: Int? = null
    var merk: String? = null
    var seri: String? = null
    var harga: String? = null

    constructor(id: Int, merk: String, seri: String, harga:String){
        this.id = id
        this.merk = merk
        this.seri = seri
        this.harga = harga
    }
}