package com.raza.auth.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.raza.auth.R

@Composable
fun MedicalImage(
    topPadding: Dp = 30.dp,
    resourceId: Int? = R.drawable.baseline_medical_services_24) {

    Image(
        painter = painterResource(
            id = resourceId!!

        ),
        contentDescription = "login icon",
        modifier = Modifier
            .padding(top = topPadding)
            .size(120.dp)

        ,
        colorFilter = ColorFilter.tint(
            MaterialTheme.colorScheme.onBackground
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMedicalImage() {
    MedicalImage()
}