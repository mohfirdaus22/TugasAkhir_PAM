package com.example.tugasakhir_pam.ui.Kamar.HomeKamar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasakhir_pam.ui.HomeUIStateKamar
import com.example.tugasakhir_pam.data.KamarRepository
import com.example.tugasakhir_pam.model.Penghuni
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

sealed class KamariUIState {
    data class Success(val kamar: Flow<List<Penghuni>>) : KamariUIState()
    object Error : KamariUIState()
    object Loading : KamariUIState()
}

class HomeKamarViewModel (private val kamarRepository: KamarRepository): ViewModel(){
    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }
    val homeUIStateKamar: StateFlow<HomeUIStateKamar> = kamarRepository.getAll()
        .filterNotNull()
        .map {
            HomeUIStateKamar (listKamar = it.toList(),it.size )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUIStateKamar()
        )
}