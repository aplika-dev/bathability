package com.aplika.feature.collect_point_details.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.aplika.feature.collect_point_details.state.CollectState

@Composable
internal fun CollectUI(
    state: CollectState
) {
    Row {
        Text(text = state.date, style = MaterialTheme.typography.bodyLarge)
        Text(text = state.bathabilitySituation, style = MaterialTheme.typography.bodyLarge)
        Text(text = state.rainSituation, style = MaterialTheme.typography.bodyLarge)
        Text(text = state.escherichiaColiFactor, style = MaterialTheme.typography.bodyLarge)
    }
}