package dev.aplika.feature.collect_point_detailed

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.aplika.feature.collect_point_detailed.ui.CollectUI
import dev.aplika.feature.collect_point_detailed.ui.ErrorUI
import dev.aplika.feature.collect_point_detailed.ui.HeaderUI
import dev.aplika.feature.collect_point_detailed.ui.LoadingUI

@Composable
fun CollectPointDetailedUI(
    viewModel: CollectPointDetailedViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Surface {
        when (val state = uiState) {
            CollectPointDetailedUIState.IsLoading -> LoadingUI()
            is CollectPointDetailedUIState.HasContent -> {
                Column {
                    HeaderUI(state = state.headerState)
                    state.collects.forEach { CollectUI(state = it) }
                }
            }
            CollectPointDetailedUIState.IsError ->
                ErrorUI(
                    onReloadClick = viewModel::reloadCollectPointDetails
                )
        }
    }
}