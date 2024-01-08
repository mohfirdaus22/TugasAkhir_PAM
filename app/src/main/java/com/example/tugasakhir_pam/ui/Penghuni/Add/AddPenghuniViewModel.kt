package com.example.tugasakhir_pam.ui.Penghuni.Add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tugasakhir_pam.AddUIStatePenghuni
import com.example.tugasakhir_pam.data.PenghuniRepository

class AddPenghuniViewModel (private val penghuniRepository: PenghuniRepository): ViewModel(){
    var addUIStatePenghuni by mutableStateOf(AddUIStatePenghuni())
        private set
}

