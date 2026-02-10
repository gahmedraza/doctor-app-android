package com.raza.medical.doctor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.raza.medical.doctor.navigation.DoctorNavGraph
import com.raza.medical.doctor.patientlist.PatientListScreen
import com.raza.medical.doctor.patientlist.PatientListViewModel
import com.raza.medical.doctor.ui.theme.DoctorappandroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DoctorappandroidTheme {
                DoctorNavGraph()
            }
        }
    }
}