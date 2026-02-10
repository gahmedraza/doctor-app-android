package com.raza.medical.doctor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raza.medical.common.model.Role
import com.raza.medical.common.model.User
import com.raza.medical.doctor.ui.theme.DoctorappandroidTheme

@Composable
fun PatientListScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),

        topBar = {
            PatientListTopBar()
        },

        ) { padding ->

        PatientListContent(padding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientListTopBar() {
    TopAppBar(
        title = {
            //Dr Sharma
            Text("Dr. Sharma")
        },
    )
}

@Composable
fun PatientListContent(padding: PaddingValues) {
    val topPadding = 20.dp
    val bottomPadding = 20.dp

    val list = remember {
        mutableStateListOf<User>(
            User(1, "Sharma", "sharma@test.com", Role.DOCTOR),
            User(2, "Sharma2", "sharma2@test.com", Role.DOCTOR),
        )
    }

    var query by remember { mutableStateOf("Search Patient") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        //Search Patient
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = topPadding,
                    bottom = bottomPadding
                ),
            value = query,
            onValueChange = {
                query = it
            }
        )

        Row(
            modifier = Modifier.padding(
                top = topPadding,
                bottom = bottomPadding
            )
        ) {
            Text("Patient Name")
            Spacer(modifier = Modifier.weight(1f))
            Text("Last Visit")
        }

        LazyColumn(
            modifier = Modifier
                .padding(
                    padding
                )
        ) {
            //Patient Name
            //Last Visit

            items(list) { user ->
                Row(
                    modifier = Modifier
                        .padding(
                            top = topPadding,
                            bottom = bottomPadding
                        )
                ) {

                    Text(
                        text = user.name
                    )
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "today"
                    )
                }
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = topPadding,
                    bottom = bottomPadding
                ),

            onClick = {}
        ) {

            Text("Add New Patient")
        }
    }
}

@Preview(showBackground = false)
@Composable
fun Preview() {
    DoctorappandroidTheme {
        PatientListScreen()
    }
}