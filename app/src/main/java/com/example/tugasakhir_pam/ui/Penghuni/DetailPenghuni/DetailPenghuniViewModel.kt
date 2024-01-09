package com.example.tugasakhir_pam.ui.Penghuni.DetailPenghuni

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasakhir_pam.DetailUIStatePenghuni
import com.example.tugasakhir_pam.data.PenghuniRepository
import com.example.tugasakhir_pam.toDetailPenghuni
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailPenghuniViewModel (
    savedStateHandle: SavedStateHandle,
    private val repository: PenghuniRepository
): ViewModel(){
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val penghuniId: String = checkNotNull(savedStateHandle[DetailDestinationPenghuni.penghuniId])
    val uiStatePenghuni: StateFlow<DetailUIStatePenghuni> =
        repository.getPenghuniById(penghuniId)
            .filterNotNull()
            .map {
                DetailUIStatePenghuni(addEventPenghuni = it.toDetailPenghuni())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailUIStatePenghuni()
            )
    suspend fun deletePenghuni(){
        repository.delete(penghuniId)
    }
}