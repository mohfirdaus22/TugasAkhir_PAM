package com.example.tugasakhir_pam.model

data class Penghuni(
    val id: String,
    val name: String,
    val alamat: String,
    val nohp: String,
    val email: String
){
    constructor(): this("","","","","")
}
