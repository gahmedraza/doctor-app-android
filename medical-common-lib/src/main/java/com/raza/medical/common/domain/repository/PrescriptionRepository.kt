package com.raza.medical.common.domain.repository

import com.raza.medical.common.domain.model.Prescription

interface PrescriptionRepository {

    suspend fun getPrescriptionsForPatient(patientId: String):
            List<Prescription>

    suspend fun getPrescriptionById(prescriptionId: String):
            Prescription?
}