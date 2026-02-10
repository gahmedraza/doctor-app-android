package com.raza.medical.common.model

import java.time.Instant

data class Prescription(
    val id: Long,

    val patientId: Long,

    val doctorId: Long,

    val doctorName: String,

    val diagnosis: String,

    val medicines: List<Medicine>,

    val notes: String,

    val createdAt: Instant
)