package dev.aplika.feature.collect_point_detailed

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.aplika.feature.collect_point_detailed.ui.CollectUI
import dev.aplika.feature.collect_point_detailed.ui.HeaderUI
import dev.aplika.feature.collect_point_detailed.ui.InAppReviewUI

@Composable
fun CollectPointDetailedUI(
    viewModel: CollectPointDetailedViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Surface {
        Column {
            when (val state = uiState) {
                CollectPointDetailedUIState.IsError -> TODO()
                is CollectPointDetailedUIState.HasContent -> {
                    HeaderUI(state = state.headerState)
                    state.collects.forEach { collect ->
                        CollectUI(state = collect)
                    }

                    if (state.shouldShowInAppReview) {
                        InAppReviewUI()
                    }
                }
            }
        }
    }
}