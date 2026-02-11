package com.raza.medical.common.di

import com.raza.medical.common.data.repository.FakePatientRepository
import com.raza.medical.common.data.repository.FakePrescriptionRepository
import com.raza.medical.common.domain.repository.PatientRepository
import com.raza.medical.common.domain.repository.PrescriptionRepository

object RepositoryProvider {

    fun providePatientRepository(): PatientRepository {
        return FakePatientRepository()
    }

    fun providePrescriptionRepository(): PrescriptionRepository {
        return FakePrescriptionRepository()
    }
}