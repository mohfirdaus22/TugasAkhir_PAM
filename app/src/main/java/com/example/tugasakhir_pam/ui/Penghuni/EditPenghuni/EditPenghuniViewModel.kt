package com.example.tugasakhir_pam.ui.Penghuni.EditPenghuni

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasakhir_pam.data.PenghuniRepository
import com.example.tugasakhir_pam.ui.AddEventPenghuni
import com.example.tugasakhir_pam.ui.AddUIStatePenghuni
import com.example.tugasakhir_pam.ui.toPenghuni
import com.example.tugasakhir_pam.ui.toUIStatePenghuni
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditPenghuniViewModel (
    savedStateHandle: SavedStateHandle,
    private val repository: PenghuniRepository
    ) : ViewModel(){
    var penghuniUIState by mutableStateOf(AddUIStatePenghuni())
        private set
    private val penghuniId: String = checkNotNull(savedStateHandle[EditDestinationPenghuni.penghuniId])
    init {
        viewModelScope.launch {
            penghuniUIState =
                repository.getPenghuniById(penghuniId)
                    .filterNotNull()
                    .first()
                    .toUIStatePenghuni()
        }
    }
    fun updateUIStatePenghuni(addEventPenghuni: AddEventPenghuni) {
        penghuniUIState = penghuniUIState.copy(addEventPenghuni = addEventPenghuni)
    }

    suspend fun updatePenghuni() {
        repository.update(penghuniUIState.addEventPenghuni.toPenghuni())
    }
}