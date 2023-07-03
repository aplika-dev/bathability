package com.aplika.feature.collect_point_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.aplika.feature.collect_point_details.ui.CollectUI
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

@Composable
fun CollectPointDetailsUI(
    navController: NavController,
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
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .padding(horizontal = 24.dp)
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

}