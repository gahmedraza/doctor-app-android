package com.raza.medical.common.data.repository

import com.raza.medical.common.domain.model.Medicine
import com.raza.medical.common.domain.model.Prescription
import com.raza.medical.common.domain.repository.PrescriptionRepository
import kotlinx.coroutines.delay
import java.time.LocalDate

class FakePrescriptionRepository: PrescriptionRepository {
    override suspend fun getPrescriptionsForPatient(patientId: String): List<Prescription> {
        delay(500)

        return listOf(
            Prescription(
                id = "rx1",
                patientId = patientId,
                date = LocalDate.of(2026, 2, 8),
                diagnosis = "Viral Fever",
                medicines = listOf(
                    Medicine(
                        "Paracetamol",
                        "500mg",
                        "Twice a day"
                    ),
                    Medicine(
                        "Cetrizine",
                        "10mg",
                        "Once at night"
                    )
                )
            ),
            Prescription(
                id = "rx2",
                patientId = patientId,
                date = LocalDate.of(2026, 1, 20),
                diagnosis = "Cold & Cough",
                medicines = listOf(
                    Medicine(
                        "Cough Syrup",
                        "10ml",
                        "Twice a day"
                    ),
                    Medicine(
                        "Azithromycin",
                        "500mg",
                        "Once a day"
                    )
                )
            )
        )
    }

    override suspend fun getPrescriptionById(prescriptionId: String): Prescription? {
        return getPrescriptionsForPatient("p1")
            .find { it.id == prescriptionId }
    }

}