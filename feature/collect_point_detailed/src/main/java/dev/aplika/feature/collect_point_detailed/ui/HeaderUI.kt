package dev.aplika.feature.collect_point_detailed.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.aplika.feature.collect_point_detailed.state.HeaderState

@Composable
internal fun HeaderUI(
    state: HeaderState
) {
    Text(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(top = 24.dp),
        text = state.title,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.ExtraBold
    )
    Text(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .padding(horizontal = 24.dp),
        text = state.subtitle,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold
    )
}