package com.raza.medical.common.repository

import com.raza.medical.common.model.Prescription

interface PrescriptionRepository {

    /**
     * Doctor creates a new prescription for a patient
     */
    suspend fun createPrescription(prescription: Prescription): Prescription

    /**
     * Doctor fetches prescriptions of a patient
     */
    suspend fun getPrescriptionsForPatient(patientId: Long): List<Prescription>

    /**
     * Patient fetches own prescriptions
     */
    suspend fun getPrescriptionsForCurrentPatient(): List<Prescription>
}