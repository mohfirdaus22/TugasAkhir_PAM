package com.example.tugasakhir_pam

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tugasakhir_pam.ui.Kamar.AddKamar.AddKamarViewModel
import com.example.tugasakhir_pam.ui.Kamar.DetailKamar.DetailKamarViewModel
import com.example.tugasakhir_pam.ui.Kamar.HomeKamar.HomeKamarViewModel
import com.example.tugasakhir_pam.ui.Penghuni.AddPenghuni.AddPenghuniViewModel
import com.example.tugasakhir_pam.ui.Penghuni.DetailPenghuni.DetailPenghuniViewModel
import com.example.tugasakhir_pam.ui.Penghuni.HomePenghuni.HomePenghuniViewModel

fun CreationExtras.aplikasiAsrama(): AsramaApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AsramaApplication)

object PenyediaViewModel {
    val Factory = viewModelFactory {

        initializer {
            HomePenghuniViewModel(aplikasiAsrama().container.penghuniRepository)
        }

        initializer {
            AddPenghuniViewModel(aplikasiAsrama().container.penghuniRepository)
        }

        initializer {
            DetailPenghuniViewModel(
                createSavedStateHandle(),
                aplikasiAsrama().container.penghuniRepository
            )
        }
        initializer {
            HomeKamarViewModel(aplikasiAsrama().container.kamarRepository)
        }
        initializer {
            AddKamarViewModel(aplikasiAsrama().container.kamarRepository)
        }
        initializer {
            DetailKamarViewModel(
                createSavedStateHandle(),
                aplikasiAsrama().container.kamarRepository
            )
        }
    }
}
