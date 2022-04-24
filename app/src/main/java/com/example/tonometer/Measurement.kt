package com.example.tonometer

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime
import java.util.*

@Parcelize
data class Measurement(
    var id: Long = Date().time,
    var date: Date = Date(),
    var systolicPressure: Int = 0,
    var diastolicPressure: Int = 0,
    var heartRate: Int = 60
): Parcelable
