package com.raza.medical.common.repository

import com.raza.medical.common.model.Prescription
import kotlinx.coroutines.delay
import java.time.Instant
import java.util.concurrent.atomic.AtomicLong

class MockPrescriptionRepository : PrescriptionRepository {

    private val prescriptions = mutableListOf<Prescription>()
    private val idGenerator = AtomicLong(100)

    override suspend fun createPrescription(prescription: Prescription): Prescription {
        delay(200)

        val newPrescription = prescription.copy(
            id = idGenerator.getAndIncrement(),
            createdAt = Instant.now()
        )

        prescriptions.add(newPrescription)

        return newPrescription
    }

    override suspend fun getPrescriptionsForPatient(patientId: Long): List<Prescription> {
        delay(200)

        return prescriptions.filter { it.patientId == patientId }
    }

    override suspend fun getPrescriptionsForCurrentPatient(): List<Prescription> {
        delay(200)

        //For mock, assuming a patientId = 10
        val currentPatientId = 10L
        return prescriptions.filter { it.patientId == currentPatientId }
    }

}