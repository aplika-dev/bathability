package com.aplika.feature.map.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.aplika.feature.map.presentation.MarkerPresentation
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.rememberMarkerState

@Composable
internal fun MarkerUI(
    marker: MarkerPresentation
) {
    Marker(
        state = rememberMarkerState(position = marker.position),
        icon = BitmapDescriptorFactory.defaultMarker(marker.color),
        onClick = {
            Log.d("xxx", "test")
            true
        }
    )
}