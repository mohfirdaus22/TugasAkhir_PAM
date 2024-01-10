package com.example.tugasakhir_pam.ui.Penghuni.HomePenghuni

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasakhir_pam.ui.HomeUIStatePenghuni
import com.example.tugasakhir_pam.data.PenghuniRepository
import com.example.tugasakhir_pam.model.Penghuni
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

sealed class PenghuniUIState {
    data class Success(val penghuni: Flow<List<Penghuni>>) : PenghuniUIState()
    object Error : PenghuniUIState()
    object Loading : PenghuniUIState()
}

class HomePenghuniViewModel (private val penghuniRepository: PenghuniRepository): ViewModel(){
    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }
    val homeUIStatePenghuni: StateFlow<HomeUIStatePenghuni> = penghuniRepository.getAll()
        .filterNotNull()
        .map {
            HomeUIStatePenghuni (listPenghuni = it.toList(),it.size )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUIStatePenghuni()
        )
}