package com.aplika.feature.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.google.maps.android.compose.GoogleMap

@Composable
fun MapUI(
    navController: NavController,
    viewModel: MapViewModel = hiltViewModel()
) {
    val mapUiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Set properties using MapProperties which you can use to recompose the map
    val mapProperties = MapProperties(
        // Only enable if user has accepted location permissions.
        isMyLocationEnabled = false,
    )
    val cameraPositionState = rememberCameraPositionState()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = mapProperties,
            cameraPositionState = cameraPositionState
        ) {
            mapUiState.locationList.forEach {
                MarkerInfoWindow(
                    state = rememberMarkerState(position = LatLng(it.latitude, it.longitude)),
                    snippet = "Some stuff",
                    onClick = {
                        // This won't work :(
                        println("Mitchs_: Cannot be clicked")
                        true
                    },
                    draggable = true
                )
            }
        }
    }
//    // Center camera to include all the Zones.
//    LaunchedEffect(state.clusterItems) {
//        if (state.clusterItems.isNotEmpty()) {
//            cameraPositionState.animate(
//                update = CameraUpdateFactory.newLatLngBounds(
//                    calculateZoneViewCenter(),
//                    0
//                ),
//            )
//        }
//    }
}