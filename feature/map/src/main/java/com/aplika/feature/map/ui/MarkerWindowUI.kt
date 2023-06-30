package com.aplika.feature.map.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aplika.feature.map.presentation.MarkerWindowPresentation

@Composable
fun MarkerWindowUI(
    markerWindowPresentation: MarkerWindowPresentation
) {
    OutlinedCard {
        Column(
            modifier = Modifier.padding(all = 16.dp)
        ) {
            Text(text = markerWindowPresentation.title, style = MaterialTheme.typography.headlineSmall)
            Text(text = markerWindowPresentation.description, style = MaterialTheme.typography.bodyMedium)

            markerWindowPresentation.collects.forEach {
                Text(text = it.date, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}