package com.aplika.feature.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aplika.core.navigation.destination.CollectPointDetailsDestination
import com.aplika.feature.map.ui.MarkerUI
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.GoogleMap

@Composable
fun MapUI(
    navController: NavController,
    viewModel: MapViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = MapProperties(
                isMyLocationEnabled = false,
            ),
            cameraPositionState = rememberCameraPositionState()
        ) {
            uiState.locationList.forEach { state ->
                MarkerUI(state = state, onMarkerClick = {
                    navController.navigate(CollectPointDetailsDestination(id = it))
                })
            }
        }
    }
}