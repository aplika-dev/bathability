package com.aplika.feature.map.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.aplika.feature.map.presentation.MarkerInfoPresentation

@Composable
fun MarkerWindowUI(
    markerInfoPresentation: MarkerInfoPresentation
) {
    OutlinedCard {
        Text(text = markerInfoPresentation.title, style = MaterialTheme.typography.titleLarge)
        Text(text = markerInfoPresentation.description, style = MaterialTheme.typography.bodyMedium)
    }
}