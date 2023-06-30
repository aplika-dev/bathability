package com.aplika.feature.map.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aplika.feature.map.presentation.MarkerInfoPresentation

@Composable
fun MarkerWindowUI(
    markerInfoPresentation: MarkerInfoPresentation
) {
    OutlinedCard {
        Column(
            modifier = Modifier.padding(all = 8.dp)
        ) {
            Text(text = markerInfoPresentation.title, style = MaterialTheme.typography.bodyMedium)
            Text(text = markerInfoPresentation.description, style = MaterialTheme.typography.bodySmall)
        }
    }
}