package com.raza.medical.common.model

data class User(
    val id: Long,

    val name: String,

    val email: String,

    val role: Role
)

enum class Role {
    DOCTOR,
    PATIENT
}