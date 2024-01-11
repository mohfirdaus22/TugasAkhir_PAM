package com.example.tugasakhir_pam.model

data class  Penghuni(
    val id: String,
    val name: String,
    val alamat: String,
    val nohp: String,
    val email: String
){
    constructor(): this("","","","","")
}


data class Kamar(
    val id: String,
    val nokamar: String,
    val tipe: String,
    val kapasitas: String,
    val harga: String
){
constructor(): this("","","","","")
}
