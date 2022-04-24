package com.example.tonometer

import androidx.lifecycle.ViewModel

class FirestoreViewModel : ViewModel() {
    private val firestoreRepository: IFirestoreRepository = FirestoreRepository()

    suspend fun getAllMeasurements(): List<Measurement> {
        return firestoreRepository.getAllMeasurements()
    }

    fun addMeasurement(measurement: Measurement) {
        firestoreRepository.addMeasurement(measurement)
    }
}