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
    suspend fun save(kamar: Kamar): String
    suspend fun update(kamar: Kamar)
    suspend fun delete(kamarId: String)
    fun getKamarById(kamarId: String): Flow<Kamar>
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


    override suspend fun save(kamar: Kamar): String {
        return try {
            val documentReference = firestore.collection("Kamar")
                .add(kamar).await()
            // Update the Kamar with the Firestore-generated DocumentReference
            firestore.collection("Kamar").document(documentReference.id)
                .set(kamar.copy(nokamar = documentReference.id))
            "Berhasil + ${documentReference.id}"
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error adding document", e)
            "Gagal $e"
        }
    }

    override suspend fun update(kamar: Kamar) {
        firestore.collection("Kamar").document(kamar.nokamar).set(kamar).await()
    }

    override suspend fun delete(kamarId: String) {
        firestore.collection("Kamar").document(kamarId).delete().await()
    }

    override fun getKamarById(kamarId: String): Flow<Kamar> {
        return flow {
            val snapshot = firestore.collection("Kamar").document(kamarId).get().await()
            val kamar = snapshot.toObject(Kamar::class.java)
            emit(kamar!!)
        }.flowOn(Dispatchers.IO)
    }

}
