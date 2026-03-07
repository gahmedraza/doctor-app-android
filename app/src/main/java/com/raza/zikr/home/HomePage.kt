package com.raza.zikr.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomePage(
    zikrs: List<ZikrUiModel>,
    onZikrClick: (ZikrUiModel) -> Unit
) {

    val totalToday = zikrs.sumOf { it.todayCount!! }
    val totalTarget = zikrs.sumOf { it.dailyTarget!! }

    val progress =
        if (totalTarget == 0) {
            0f
        } else {
            totalToday.toFloat() /
                    totalTarget.toFloat()
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {

        Text(
            text = "Today",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        OverallProgressCard(
            totalToday = totalToday,
            totalTarget = totalTarget,
            progress = progress,
            streak = zikrs.maxOfOrNull { it.streak!! } ?: 0
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(zikrs, key = { it.id!! }) { zikr ->
                ZikrRow(
                    zikr = zikr,
                    onClick = {
                        onZikrClick(zikr)
                    }
                )
            }
        }
    }
}