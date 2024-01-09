package com.example.tugasakhir_pam.ui.Penghuni.HomePenghuni

import androidx.lifecycle.ViewModel
import com.example.tugasakhir_pam.data.PenghuniRepository
import com.example.tugasakhir_pam.model.Penghuni
import kotlinx.coroutines.flow.Flow

sealed class PenghuniUIState {
    data class Success(val penghuni: Flow<List<Penghuni>>) : PenghuniUIState()
    object Error : PenghuniUIState()
    object Loading : PenghuniUIState()
}

class HomePenghuniViewModel (private val penghuniRepository: PenghuniRepository): ViewModel(){
    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }
}