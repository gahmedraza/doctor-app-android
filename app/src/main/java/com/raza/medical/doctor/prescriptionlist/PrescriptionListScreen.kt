package com.raza.medical.doctor.prescriptionlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raza.medical.common.domain.model.Prescription
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrescriptionListScreen(

    patientId: String,

    onBack: () -> Unit,

    onPrescriptionClick: (Prescription) -> Unit,

    viewModel: PrescriptionListViewModel = viewModel()

) {

    val prescriptions by viewModel.prescriptions.collectAsState()

    LaunchedEffect(patientId) {
        viewModel.loadPrescriptions(patientId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Prescriptions"
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

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            items(prescriptions) { prescription ->
                PrescriptionRow(
                    prescription = prescription,
                    onClick = {
                        onPrescriptionClick(prescription)
                    }
                )
                Divider()
            }
        }
    }

}

@Composable
fun PrescriptionRow(
    prescription: Prescription,
    onClick: () -> Unit
) {
    val formatter = DateTimeFormatter.ofPattern(
        "dd MMM yyyy"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Text(
            text = prescription.date.format(formatter),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = prescription.diagnosis,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Medicines: ${prescription.medicines.size}",
            style = MaterialTheme.typography.bodySmall
        )
    }
}