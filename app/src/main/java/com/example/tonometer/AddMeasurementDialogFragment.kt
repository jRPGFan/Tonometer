package com.example.tonometer

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.tonometer.databinding.FragmentAddMeasurementBinding
import java.time.LocalDateTime
import java.util.*

class AddMeasurementDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentAddMeasurementBinding
    private lateinit var firestoreDB: FirestoreViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentAddMeasurementBinding.inflate(layoutInflater)
        binding.buttonCancel.setOnClickListener{ dismiss() }
        binding.buttonSave.setOnClickListener { saveMeasurement() }
        return AlertDialog.Builder(context)
            .setView(binding.root)
            .create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonCancel.setOnClickListener{ dismiss() }
        binding.buttonSave.setOnClickListener { saveMeasurement() }
    }

    private fun saveMeasurement() {
        val measurement = Measurement(
            id = Date().time,
            date = Date(),
            systolicPressure = binding.systolicMeasurementInput.text.toString().toInt(),
            diastolicPressure = binding.diastolicMeasurementInput.text.toString().toInt(),
            heartRate = binding.heartRateInput.text.toString().toInt()
        )
        firestoreDB.addMeasurement(measurement)
        (requireActivity() as MainActivity).getMeasurements()
        dismiss()
    }

    companion object {
        fun newInstance(firestoreDB: FirestoreViewModel) =
            AddMeasurementDialogFragment().apply { this.firestoreDB = firestoreDB }
    }
}