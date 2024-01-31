package dev.aplika.feature.collect_point_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.aplika.feature.collect_point_details.ui.CollectUI
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import dev.aplika.feature.collect_point_details.ui.InAppReviewUI

@Composable
fun CollectPointDetailsUI(
    viewModel: CollectPointDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Surface {
        Column {
            Text(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(top = 24.dp)
                    .placeholder(
                        visible = uiState.isLoading,
                        highlight = PlaceholderHighlight.shimmer()
                    ),
                text = uiState.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 24.dp)
                    .placeholder(
                        visible = uiState.isLoading,
                        highlight = PlaceholderHighlight.shimmer()
                    ),
                text = uiState.subtitle,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 16.dp)
                    .placeholder(
                        visible = uiState.isLoading,
                        highlight = PlaceholderHighlight.shimmer()
                    ),
                text = uiState.description,
                style = MaterialTheme.typography.bodyMedium
            )
            uiState.collects.forEach {
                CollectUI(state = it)
            }
        }
    }

    InAppReviewUI()
}