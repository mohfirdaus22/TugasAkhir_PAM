package com.example.tugasakhir_pam.ui

import com.example.tugasakhir_pam.model.Kamar
import com.example.tugasakhir_pam.model.Penghuni

data class HomeUIStatePenghuni(
    val listPenghuni: List<Penghuni> = listOf(),
    val dataLength: Int = 0
)

data class HomeUIStateKamar(
    val listKamar: List<Kamar> = listOf(),
    val dataLength: Int = 0
)

data class  AddEventPenghuni(
    val id: String = "",
    val name: String = "",
    val alamat: String = "",
    val nohp: String = "",
    val email: String = ""
)

data class  AddEventKamar(
    val nokamar: String = "",
    val tipe: String = "",
    val kapasitas: String = "",
    val harga: String = ""
)

fun Penghuni.toDetailPenghuni(): AddEventPenghuni = AddEventPenghuni(
    id = id,
    name = name,
    alamat = alamat,
    nohp = nohp,
    email = email
)

fun Kamar.toDetailKamar(): AddEventKamar = AddEventKamar(
    nokamar = nokamar,
    tipe = tipe,
    kapasitas = kapasitas,
    harga = harga
)

fun AddEventPenghuni.toPenghuni() = Penghuni(
    id = id,
    name = name,
    alamat = alamat,
    nohp = nohp,
    email = email
)

fun AddEventKamar.toKamar() = Kamar(
    nokamar = nokamar,
    tipe = tipe,
    kapasitas = kapasitas,
    harga = harga
)

data class AddUIStatePenghuni(
    val addEventPenghuni: AddEventPenghuni = AddEventPenghuni(),
)

data class AddUIStateKamar(
    val addEventKamar: AddEventKamar = AddEventKamar()
)

fun Penghuni.toUIStatePenghuni(): AddUIStatePenghuni = AddUIStatePenghuni(
    addEventPenghuni = this.toDetailPenghuni()
)

fun Kamar.toUIStateKamar(): AddUIStateKamar = AddUIStateKamar(
    addEventKamar = this.toDetailKamar()
)

data class DetailUIStatePenghuni(
    val  addEventPenghuni: AddEventPenghuni = AddEventPenghuni()
)

data class DetailUIStateKamar(
    val addEventKamar: AddEventKamar = AddEventKamar()
)