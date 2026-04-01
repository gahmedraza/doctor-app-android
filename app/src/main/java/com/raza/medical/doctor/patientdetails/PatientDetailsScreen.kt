package com.raza.medical.doctor.patientdetails

import android.widget.TextView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raza.medical.common.di.RepositoryProvider
import com.raza.medical.common.domain.model.Patient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientDetailsScreen(
    patientId: String,
    onBack: () -> Unit,
    onViewPrescription: (String) -> Unit
) {

    val repository = RepositoryProvider.providePatientRepository()
    var patient by remember { mutableStateOf<Patient?>(null) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(patientId) {
        patient = repository.getPatientById(patientId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Patient Details"
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack
                    ) {
                        Text("<-")
                    }
                }
            )
        }
    ) { padding ->

        patient?.let {

            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {

                Text(
                    it.name,
                    style =
                        MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Age: ${it.age}")
                Text("Gender: ${it.gender}")
                Text("Last Visit: ${it.lastVisitDate}")

                Spacer(
                    modifier =
                        Modifier.height(24.dp)
                )

                Button(onClick = {
                    onViewPrescription(patientId)
                }) {
                    Text(
                        "View Prescriptions"
                    )
                }
            }

        } ?: Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

    }
}