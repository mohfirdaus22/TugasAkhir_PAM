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
    fun getKamarById(kamarId: String): Flow<Penghuni>
}
class KamarRepositoryImpl(private val firestore: FirebaseFirestore) : KamarRepository {
    override fun getAll(): Flow<List<Kamar>> = flow {
        val snapshot = firestore.collection("Kamar")
            .orderBy("nama", Query.Direction.ASCENDING)
            .get()
            .await()
        val kamar = snapshot.toObjects(Kamar::class.java)
        emit(kamar)
    }.flowOn(Dispatchers.IO)


    override suspend fun save(asrama: Kamar): String {
        return try {
            val documentReference = firestore.collection("Kamar").add(asrama).await()
            // Update the Kamar with the Firestore-generated DocumentReference
            firestore.collection("Kamar").document(documentReference.id)
                .set(asrama.copy(nokamar = documentReference.id))
            "Berhasil + ${documentReference.id}"
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error adding document", e)
            "Gagal $e"
        }
    }


}
