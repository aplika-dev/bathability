package com.aplika.feature.map.state

import androidx.annotation.DrawableRes
import com.google.android.gms.maps.model.LatLng

data class MarkerState(
    val id: String,
    @DrawableRes val iconResId: Int,
    val position: LatLng
)
