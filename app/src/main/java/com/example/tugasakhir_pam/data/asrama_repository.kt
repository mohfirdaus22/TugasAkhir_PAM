package com.example.tugasakhir_pam.data

import android.content.ContentValues
import android.util.Log
import com.example.tugasakhir_pam.model.Penghuni
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

interface PenghuniRepository {
    fun getAll(): Flow<List<Penghuni>>
    suspend fun save(asrama: Penghuni): String
    suspend fun update(asrama: Penghuni)
    suspend fun delete(penghuniId: String)
    fun getKontakById(penghuniId: String): Flow<Penghuni>
}
