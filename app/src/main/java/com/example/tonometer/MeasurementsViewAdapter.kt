package com.example.tonometer

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tonometer.databinding.TonometerRecordItemBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.time.Duration.Companion.hours

class MeasurementsViewAdapter() : RecyclerView.Adapter<MeasurementsViewAdapter.ViewHolder>() {
    private var measurements: List<Measurement>? = null
    private var currentItemDate: Date? = null

    fun updateList(measurementsList: List<Measurement>) {
        measurements = measurementsList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = measurements?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        measurements?.get(position)?.let { holder.bind(it) }
    }

    inner class ViewHolder(
        parent: ViewGroup, private val binding: TonometerRecordItemBinding =
            TonometerRecordItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(measurement: Measurement) {
            if (currentItemDate == null || currentItemDate!!.dateLaterThanCurrent(measurement.date)) {
                binding.date.visibility = View.VISIBLE
                binding.date.text = measurement.date.dateToString("dd MMMM")
                currentItemDate = measurement.date
            }
            binding.root.background = MeasurementResultGradient().getGradient(measurement.systolicPressure, measurement.diastolicPressure)
            binding.time.text = measurement.date.dateToString("HH:mm")
            binding.systolicPressure.text = measurement.systolicPressure.toString()
            binding.diastolicPressure.text = measurement.diastolicPressure.toString()
            binding.heartRate.text = measurement.heartRate.toString()
        }
    }

    private fun Date.dateToString(format: String): String {
        val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
        return dateFormatter.format(this)
    }

    private fun Date.dateLaterThanCurrent(nDate: Date): Boolean {
        return ChronoUnit.DAYS.between(this.toInstant(), nDate.toInstant()) > 0
    }
}