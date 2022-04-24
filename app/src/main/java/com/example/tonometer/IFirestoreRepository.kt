package com.example.tonometer

interface IFirestoreRepository {
    suspend fun getAllMeasurements(): List<Measurement>
    fun addMeasurement(measurement: Measurement)
}