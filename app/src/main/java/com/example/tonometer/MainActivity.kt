package com.example.tonometer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.tonometer.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MeasurementsViewAdapter
    private lateinit var firestoreDB: FirestoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        firestoreDB = ViewModelProvider(this).get(FirestoreViewModel::class.java)
        adapter = MeasurementsViewAdapter()
        binding.measurementsList.adapter = adapter
        getMeasurements()
        binding.addMeasurement.setOnClickListener {
            AddMeasurementDialogFragment.newInstance(firestoreDB)
                .show(supportFragmentManager, "AddMeasurementDialog")
        }
    }

    fun getMeasurements() {
        lifecycleScope.launch { adapter.updateList(firestoreDB.getAllMeasurements()) }
    }
}