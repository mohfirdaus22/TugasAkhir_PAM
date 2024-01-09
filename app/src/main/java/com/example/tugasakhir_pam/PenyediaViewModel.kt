package com.example.tugasakhir_pam

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.viewModelFactory

fun CreationExtras.aplikasiAsrama(): AsramaApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AsramaApplication)

object PenyediaViewModel {
    val Factory = viewModelFactory {

    }
}
