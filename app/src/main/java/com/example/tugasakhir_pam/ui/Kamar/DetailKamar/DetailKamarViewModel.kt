package com.example.tugasakhir_pam.ui.Kamar.DetailKamar

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasakhir_pam.ui.DetailUIStateKamar
import com.example.tugasakhir_pam.data.KamarRepository
import com.example.tugasakhir_pam.ui.toDetailKamar
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailKamarViewModel (
    savedStateHandle: SavedStateHandle,
    private val repository: KamarRepository
): ViewModel(){
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val kamarId: String = checkNotNull(savedStateHandle[DetailDestinationKamar.itemId])
    val uiStateKamar: StateFlow<DetailUIStateKamar> =
        repository.getKamarById(kamarId)
            .filterNotNull()
            .map {
                DetailUIStateKamar(addEventKamar = it.toDetailKamar())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailUIStateKamar()
            )
    suspend fun deleteKamar(){
        repository.delete(kamarId)
    }
}