package com.aplika.feature.map.ui

import androidx.compose.runtime.Composable
import com.aplika.feature.map.state.MarkerState
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberMarkerState

@Composable
internal fun MarkerUI(
    state: MarkerState,
    onMarkerClick: (id: String) -> Unit
) {
    Marker(
        state = rememberMarkerState(position = state.position),
        icon = BitmapDescriptorFactory.defaultMarker(state.color),
        onClick = {
            onMarkerClick(state.id)
            true
        }
    )
}