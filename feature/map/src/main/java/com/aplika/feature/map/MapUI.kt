package com.aplika.feature.map

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aplika.core.navigation.destination.CollectPointDetailsDestination
import com.aplika.feature.map.ui.MarkerUI
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.GoogleMap
import kotlinx.coroutines.launch

@Composable
fun MapUI(
    navController: NavController,
    viewModel: MapViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()

    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues = paddingValues)) {
            if (uiState.isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(LatLng(LATITUDE, LONGITUDE), ZOOM)
            }

            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                uiState.locationList.forEach { state ->
                    MarkerUI(
                        state = state,
                        onMarkerClick = {
                            coroutineScope.launch {
                                cameraPositionState.animate(
                                    CameraUpdateFactory.newCameraPosition(
                                        CameraPosition.fromLatLngZoom(it.position, cameraPositionState.position.zoom)
                                    )
                                )
                            }
                            navController.navigate(CollectPointDetailsDestination(id = state.id))
                        })
                }
            }
        }
    }
}

private const val LATITUDE = -27.695045265166847
private const val LONGITUDE = -49.0310188382864
private const val ZOOM = 7.975901F