package com.example.tugasakhir_pam.data

import com.google.firebase.firestore.FirebaseFirestore


interface AppContainer {
    val penghuniRepository: PenghuniRepository
    val kamarRepository: KamarRepository
}
class AsramaContainer : AppContainer{
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override val penghuniRepository: PenghuniRepository by lazy {
        PenghuniRepositoryImpl(firestore)
    }

    override val kamarRepository: KamarRepository by lazy {
        KamarRepositoryImpl(firestore)
    }
}