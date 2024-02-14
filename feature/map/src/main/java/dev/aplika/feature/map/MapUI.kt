package dev.aplika.feature.map

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.aplika.core.navigation.destination.CollectPointDetailsDestination
import dev.aplika.core.resources.R
import dev.aplika.feature.map.ui.MarkerUI
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import dev.aplika.feature.map.ui.InAppReviewUI
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapUI(
    navController: NavController,
    drawerState: DrawerState,
    viewModel: MapViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    val locationPermissionState = rememberPermissionState(
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

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
                properties = MapProperties(
                    isMyLocationEnabled = locationPermissionState.status.isGranted
                ),
                cameraPositionState = cameraPositionState
            ) {
                uiState.locationList.forEach { state ->
                    MarkerUI(
                        state = state,
                        onMarkerClick = {
                            coroutineScope.launch {
                                cameraPositionState.animate(
                                    CameraUpdateFactory.newCameraPosition(
                                        CameraPosition.fromLatLngZoom(
                                            it.position,
                                            cameraPositionState.position.zoom
                                        )
                                    )
                                )
                            }
                            navController.navigate(CollectPointDetailsDestination(localityGroup = state.localityGroup, id = state.id))
                        })
                }
            }
        }
    }

    FloatingActionButton(
        modifier = Modifier.padding(all = 16.dp),
        onClick = { coroutineScope.launch { drawerState.open() } }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_menu), contentDescription = stringResource(
                id = R.string.ic_menu_content_description
            )
        )
    }

    LaunchedEffect(key1 = locationPermissionState) {
        if (!locationPermissionState.status.isGranted) {
            locationPermissionState.launchPermissionRequest()
        }
    }

    val shouldShowInAppReview by viewModel.shouldShowInAppReview.collectAsStateWithLifecycle()
    if (shouldShowInAppReview) {
        InAppReviewUI()
    }
}

private const val LATITUDE = -29.082949890878332
private const val LONGITUDE = -52.4694823846221
private const val ZOOM = 5.8766937F