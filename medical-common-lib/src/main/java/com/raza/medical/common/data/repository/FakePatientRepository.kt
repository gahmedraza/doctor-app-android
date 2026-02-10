package com.raza.medical.common.data.repository

import com.raza.medical.common.domain.model.Gender
import com.raza.medical.common.domain.model.Patient
import com.raza.medical.common.domain.repository.PatientRepository
import kotlinx.coroutines.delay
import java.time.LocalDate

class FakePatientRepository : PatientRepository {

    override suspend fun getPatientsForDoctor(doctorId: String): List<Patient> {
        delay(500)

        return listOf(
            Patient(
                id = "p1",
                name = "Alice Kumar",
                age = 30,
                gender = Gender.FEMALE,
                lastVisitDate = LocalDate.of(2026, 2, 8)
            ),

            Patient(

                id = "p2",
                name = "Bob Sharma",
                age = 45,
                gender = Gender.MALE,
                lastVisitDate = LocalDate.of(2026, 2, 6)
            ),

            Patient(

                id = "p3",
                name = "Alice Khan",
                age = 48,
                gender = Gender.FEMALE,
                lastVisitDate = LocalDate.of(2026, 2, 3)
            )
        )
    }

    override suspend fun getPatientById(patientId: String): Patient? {
        return getPatientsForDoctor(
            "doctor1"
        ).find {
            it.id == patientId
        }
    }

}