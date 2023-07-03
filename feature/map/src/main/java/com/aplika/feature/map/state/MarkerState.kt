package com.aplika.feature.map.state

import com.google.android.gms.maps.model.LatLng

data class MarkerState(
    val id: String,
    val color: Float,
    val position: LatLng
)
