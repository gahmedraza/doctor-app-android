package com.raza.medical.doctor.prescriptiondetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raza.medical.common.di.RepositoryProvider
import com.raza.medical.common.domain.model.Prescription
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrescriptionDetailScreen(
    //prescription: Prescription,
    //patientName: String?,
    prescriptionId: String,
    onBack: () -> Unit
) {
    val repository = RepositoryProvider.providePrescriptionRepository()
    var prescription by remember { mutableStateOf<Prescription?>(null)}

    LaunchedEffect(prescriptionId) {
        prescription = repository.getPrescriptionById(prescriptionId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text =
                            "Prescription"
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

        prescription?.let { rx ->
            val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")

            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {

                //Diagnosis
                Text(text = "Diagnosis")
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = rx.diagnosis)

                //Date
                Text(text = "Date")
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = rx.date.format(formatter))

                //Medicine list
            }
        } ?: Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier
                .height(16.dp))
            CircularProgressIndicator()
        }
    }
}