package com.raza.medical.doctor.prescriptionlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raza.medical.common.di.RepositoryProvider
import com.raza.medical.common.domain.model.Prescription
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PrescriptionListViewModel : ViewModel() {

    private val prescriptionRepository =
        RepositoryProvider.providePrescriptionRepository()

    private val _prescriptions =
        MutableStateFlow<List<Prescription>>(emptyList())

    val prescriptions: StateFlow<List<Prescription>> = _prescriptions

    fun loadPrescriptions(patientId: String) {
        viewModelScope.launch {
            val result =
                prescriptionRepository.getPrescriptionsForPatient(patientId)

            _prescriptions.value = result

        }
    }
}