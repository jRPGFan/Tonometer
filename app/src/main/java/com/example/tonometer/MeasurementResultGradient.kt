package com.example.tonometer

import android.graphics.Color
import android.graphics.drawable.GradientDrawable

class MeasurementResultGradient {
    fun getGradient(systolicPressure: Int, diastolicPressure: Int, ): GradientDrawable {
        val gradient = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(Color.WHITE, Color.WHITE, systolicPressureCondition(systolicPressure),
                diastolicPressureCondition(diastolicPressure), Color.WHITE, Color.WHITE))
        gradient.cornerRadius = 45f
        gradient.shape = GradientDrawable.OVAL
        return gradient
    }

    private fun systolicPressureCondition(systolicPressure: Int): Int {
        return when {
            systolicPressure < 90 -> Color.BLUE
            systolicPressure in 90..119 -> Color.GREEN
            systolicPressure in 120..139 -> Color.YELLOW
            systolicPressure > 140 -> Color.RED
            else -> Color.GREEN
        }
    }

    private fun diastolicPressureCondition(diastolicPressure: Int): Int {
        return when {
            diastolicPressure < 60 -> Color.BLUE
            diastolicPressure in 60..79 -> Color.GREEN
            diastolicPressure in 80..89 -> Color.YELLOW
            diastolicPressure > 90 -> Color.RED
            else -> Color.GREEN
        }
    }
}