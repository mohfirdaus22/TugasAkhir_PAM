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
    suspend fun save(penghuni: Penghuni): String
    suspend fun update(penghuni: Penghuni )
    suspend fun delete(penghuniId: String)
    fun getPenghuniById(penghuniId: String): Flow<Penghuni>
}
class PenghuniRepositoryImpl(private val firestore: FirebaseFirestore) : PenghuniRepository {
    override fun getAll(): Flow<List<Penghuni>> = flow {
        val snapshot = firestore.collection("Penghuni")
            .orderBy("nama", Query.Direction.ASCENDING)
            .get()
            .await()
        val penghuni = snapshot.toObjects(Penghuni::class.java)
        emit(penghuni)
    }.flowOn(Dispatchers.IO)


    override suspend fun save(asrama: Penghuni): String {
        return try {
            val documentReference = firestore.collection("Penghuni").add(asrama).await()
            // Update the Penghuni with the Firestore-generated DocumentReference
            firestore.collection("Penghuni").document(documentReference.id)
                .set(asrama.copy(id = documentReference.id))
            "Berhasil + ${documentReference.id}"
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error adding document", e)
            "Gagal $e"
        }
    }
    override suspend fun update(asrama: Penghuni) {
        firestore.collection("Penghuni").document(asrama.id).set(asrama).await()
    }

    override suspend fun delete(penghuniId: String) {
        firestore.collection("Penghuni").document(penghuniId).delete().await()
    }

    override fun getPenghuniById(penghuniId: String): Flow<Penghuni> {
        return flow {
            val snapshot = firestore.collection("Penghuni").document(penghuniId).get().await()
            val penghuni = snapshot.toObject(Penghuni::class.java)
            emit(penghuni!!)
        }.flowOn(Dispatchers.IO)
    }


}