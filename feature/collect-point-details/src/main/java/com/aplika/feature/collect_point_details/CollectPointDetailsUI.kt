package com.aplika.feature.collect_point_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.aplika.feature.collect_point_details.ui.CollectUI

@Composable
fun CollectPointDetailsUI(
    navController: NavController,
    viewModel: CollectPointDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.padding(all = 24.dp)) {
        Text(text = uiState.title, style = MaterialTheme.typography.headlineSmall)
        Text(modifier = Modifier.padding(top = 16.dp), text = uiState.title, style = MaterialTheme.typography.bodyMedium)

        uiState.collects.forEach {
            CollectUI(state = it)
        }
    }
}