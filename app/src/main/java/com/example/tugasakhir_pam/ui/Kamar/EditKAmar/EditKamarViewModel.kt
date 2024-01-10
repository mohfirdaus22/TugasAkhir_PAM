package com.example.tugasakhir_pam.ui.Kamar.EditKAmar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasakhir_pam.AddEventKamar
import com.example.tugasakhir_pam.AddUIStateKamar
import com.example.tugasakhir_pam.data.KamarRepository
import com.example.tugasakhir_pam.toKamar
import com.example.tugasakhir_pam.toUIStateKamar
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditKamarViewModel (
    savedStateHandle: SavedStateHandle,
    private val repository: KamarRepository
) : ViewModel(){
    var kamariUIState by mutableStateOf(AddUIStateKamar())
        private set

    private val kamarId: String = checkNotNull(savedStateHandle[EditDestinationKamar.kamarId])

    init {
        viewModelScope.launch {
            kamariUIState =
                repository.getKamarById(kamarId)
                    .filterNotNull()
                    .first()
                    .toUIStateKamar()
        }
    }
    fun updateUIStateKamar(addEventKamar: AddEventKamar){
        kamariUIState = kamariUIState.copy(addEventKamar = addEventKamar)
    }

    suspend fun updateKamar(){
        repository.update(kamariUIState.addEventKamar.toKamar())
    }
}