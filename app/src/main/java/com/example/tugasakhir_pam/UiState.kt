package com.example.tugasakhir_pam

import com.example.tugasakhir_pam.model.Penghuni

data class HomeUIStatePenghuni(
    val listAnggota: List<Penghuni> = listOf(),
    val dataLength: Int = 0
)

data class  AddEventPenghuni(
    val id: String = "",
    val name: String = "",
    val alamat: String = "",
    val nohp: String = "",
    val email: String = ""
)
