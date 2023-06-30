package com.aplika.feature.map.presentation

import com.google.android.gms.maps.model.LatLng

data class MarkerPresentation(
    val id: String,
    val color: Float,
    val position: LatLng
)
