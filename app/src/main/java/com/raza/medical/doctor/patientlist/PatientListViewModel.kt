package com.raza.medical.doctor.patientlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raza.medical.common.di.RepositoryProvider
import com.raza.medical.common.domain.model.Patient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PatientListViewModel: ViewModel() {
    private val patientRepository = RepositoryProvider.providePatientRepository()

    private val _patients = MutableStateFlow<List<Patient>>(emptyList())
    val patients: StateFlow<List<Patient>> = _patients

    fun loadPatients() {
        viewModelScope.launch {
            val result = patientRepository.getPatientsForDoctor(
                "doctor1"
            )
            _patients.value = result
        }
    }
}