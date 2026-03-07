package com.raza.zikr.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZikrCounterPage(
    zikrName: String = "SubhanAllah",
    target: Int = 100,
    count: Int = 45,
    onIncrement: () -> Unit = {},
    onReset: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    val progress = count.toFloat() / target.toFloat()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(zikrName)},
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .size(240.dp),
                contentAlignment = Alignment.Center) {

                Text(
                    text = "$count",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(modifier = Modifier
                    .padding(top  = 100.dp),
                    text = "of $target",
                    fontSize = 24.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onIncrement,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(Color.Yellow),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("TAP TO COUNT", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = onReset,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Reset Today")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ZikrCounterPreview() {
    ZikrCounterPage {  }
}