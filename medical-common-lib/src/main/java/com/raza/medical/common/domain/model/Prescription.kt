package com.raza.medical.common.domain.model

import java.time.LocalDate

data class Prescription(

    val id: String,

    val patientId: String,

    val date: LocalDate,

    val diagnosis: String,

    val medicines: List<Medicine>
)

data class Medicine(
    val name: String,

    val dosage: String,

    val frequency: String
)