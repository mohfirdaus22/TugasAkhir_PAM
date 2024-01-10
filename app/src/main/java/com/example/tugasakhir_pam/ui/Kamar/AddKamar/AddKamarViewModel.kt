package com.example.tugasakhir_pam.ui.Kamar.AddKamar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tugasakhir_pam.ui.AddEventKamar
import com.example.tugasakhir_pam.ui.AddUIStateKamar
import com.example.tugasakhir_pam.data.KamarRepository
import com.example.tugasakhir_pam.ui.toKamar

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