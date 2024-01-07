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
class KontakRepositoryImpl(private val firestore: FirebaseFirestore) : PenghuniRepository {
    override fun getAll(): Flow<List<Penghuni>> = flow {
        val snapshot = firestore.collection("Penghuni")
            .orderBy("nama", Query.Direction.ASCENDING)
            .get()
            .await()
        val kontak = snapshot.toObjects(Penghuni::class.java)
        emit(kontak)
    }.flowOn(Dispatchers.IO)


    override suspend fun save(penghuni: Penghuni): String {
        return try {
            val documentReference = firestore.collection("Penghuni").add(penghuni).await()
            // Update the Kontak with the Firestore-generated DocumentReference
            firestore.collection("Penghuni").document(documentReference.id)
                .set(penghuni.copy(id = documentReference.id))
            "Berhasil + ${documentReference.id}"
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error adding document", e)
            "Gagal $e"
        }
    }



}