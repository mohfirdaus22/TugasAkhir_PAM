package com.example.tugasakhir_pam.ui.Kamar.AddKamar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tugasakhir_pam.AddEventKamar
import com.example.tugasakhir_pam.AddUIStateKamar
import com.example.tugasakhir_pam.data.KamarRepository
import com.example.tugasakhir_pam.toKamar

class AddKamarViewModel (private val kamarRepository: KamarRepository): ViewModel(){
    var addUIStateKamar by mutableStateOf(AddUIStateKamar())
        private set

    fun updateAddUIStateKamar(addEventKamar: AddEventKamar) {
        addUIStateKamar = AddUIStateKamar(addEventKamar = addEventKamar)
    }
    suspend fun addKamar() {
        kamarRepository.save(addUIStateKamar.addEventKamar.toKamar())
    }
}