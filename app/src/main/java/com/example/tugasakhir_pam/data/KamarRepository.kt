package com.example.tugasakhir_pam.data

import android.content.ContentValues
import android.util.Log
import com.example.tugasakhir_pam.model.Kamar
import com.example.tugasakhir_pam.model.Penghuni
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

interface KamarRepository {
    fun getAll(): Flow<List<Kamar>>
    suspend fun save(asrama: Kamar): String
    suspend fun update(asrama: Kamar)
    suspend fun delete(kamarId: String)
    fun getPenghuniById(kamarId: String): Flow<Penghuni>
}
