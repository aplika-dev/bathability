package com.aplika.feature.map.ui

import androidx.compose.runtime.Composable
import com.aplika.feature.map.state.MarkerState
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberMarkerState

@Composable
internal fun MarkerUI(
    state: MarkerState,
    onMarkerClick: (marker: Marker) -> Unit
) {
    Marker(
        state = rememberMarkerState(position = state.position),
        icon = BitmapDescriptorFactory.defaultMarker(state.color),
        onClick = {
            onMarkerClick(it)
            true
        }
    )
}