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

fun Penghuni.toDetailPenghuni(): AddEventPenghuni = AddEventPenghuni(
    id = id,
    name = name,
    alamat = alamat,
    nohp = nohp,
    email = email
)

fun AddEventPenghuni.toPenghuni() = Penghuni(
    id = id,
    name = name,
    alamat = alamat,
    nohp = nohp,
    email = email
)

data class AddUIStatePenghuni(
    val addEventPenghuni: AddEventPenghuni = AddEventPenghuni(),
)

fun Penghuni.toUIStatePenghuni():AddUIStatePenghuni = AddUIStatePenghuni(
    addEventPenghuni = this.toDetailPenghuni()
)