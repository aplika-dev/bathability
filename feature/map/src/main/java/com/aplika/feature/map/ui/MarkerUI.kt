package com.aplika.feature.map.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.aplika.feature.map.presentation.MarkerPresentation
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.rememberMarkerState

@Composable
internal fun MarkerUI(
    marker: MarkerPresentation
) {
    MarkerInfoWindow(
        state = rememberMarkerState(position = marker.position),
        icon = BitmapDescriptorFactory.defaultMarker(marker.color)
    ) {
        MarkerWindowUI(markerInfoPresentation = marker.info)
    }
}