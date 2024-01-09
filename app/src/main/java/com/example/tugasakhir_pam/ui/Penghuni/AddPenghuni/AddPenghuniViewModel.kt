package com.example.tugasakhir_pam.ui.Penghuni.AddPenghuni

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tugasakhir_pam.AddEventPenghuni
import com.example.tugasakhir_pam.AddUIStatePenghuni
import com.example.tugasakhir_pam.data.PenghuniRepository
import com.example.tugasakhir_pam.toPenghuni

class AddPenghuniViewModel (private val penghuniRepository: PenghuniRepository): ViewModel(){
    var addUIStatePenghuni by mutableStateOf(AddUIStatePenghuni())
        private set

    fun updateAddUIStatePenghuni(addEventPenghuni: AddEventPenghuni) {
        addUIStatePenghuni = AddUIStatePenghuni(addEventPenghuni = addEventPenghuni)
    }
    suspend fun addPenghuni() {
        penghuniRepository.save(addUIStatePenghuni.addEventPenghuni.toPenghuni())
    }
}

