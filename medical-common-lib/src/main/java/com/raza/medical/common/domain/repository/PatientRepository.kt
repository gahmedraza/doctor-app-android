package com.raza.medical.common.domain.repository

import com.raza.medical.common.domain.model.Patient

interface PatientRepository {
    suspend fun getPatientsForDoctor(doctorId: String): List<Patient>

    suspend fun getPatientById(patientId: String): Patient?
}