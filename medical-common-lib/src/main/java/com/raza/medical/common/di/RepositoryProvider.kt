package com.raza.medical.common.di

import com.raza.medical.common.data.repository.FakePatientRepository
import com.raza.medical.common.domain.repository.PatientRepository

object RepositoryProvider {

    fun providePatientRepository(): PatientRepository {
        return FakePatientRepository()
    }
}