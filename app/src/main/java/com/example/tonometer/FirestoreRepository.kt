package com.example.tonometer

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.coroutines.tasks.await

class FirestoreRepository : IFirestoreRepository {
    private val firestoneDB = FirebaseFirestore.getInstance().apply {
        firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(false).build()
    }

    override suspend fun getAllMeasurements(): List<Measurement> {
        val snapshot = firestoneDB.collection(TABLE_NAME).orderBy("id").get().await()
        return snapshot.toObjects(Measurement::class.java)
    }

    override fun addMeasurement(measurement: Measurement) {
        firestoneDB.collection(TABLE_NAME).add(measurement)
    }

    companion object {
        private const val TABLE_NAME = "Measurements"
    }
}