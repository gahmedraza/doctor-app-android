package com.raza.medical.common.domain.model

import java.time.LocalDate

data class Patient(
    val id: String,

    val name: String,

    val age: Int,

    val gender: Gender,

    val lastVisitDate: LocalDate
)

enum class Gender {
    MALE,
    FEMALE,
    OTHER
}