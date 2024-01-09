package com.example.tugasakhir_pam

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tugasakhir_pam.ui.Penghuni.Add.AddPenghuniViewModel
import com.example.tugasakhir_pam.ui.Penghuni.Detail.DetailPenghuniViewModel

fun CreationExtras.aplikasiAsrama(): AsramaApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AsramaApplication)

object PenyediaViewModel {
    val Factory = viewModelFactory {


        initializer {
            AddPenghuniViewModel(aplikasiAsrama().container.penghuniRepository)
        }

        initializer {
            DetailPenghuniViewModel(
                createSavedStateHandle(),
                aplikasiAsrama().container.penghuniRepository
            )
        }
    }
}
