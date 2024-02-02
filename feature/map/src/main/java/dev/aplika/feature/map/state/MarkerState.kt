package dev.aplika.feature.map.state

import androidx.annotation.DrawableRes
import com.google.android.gms.maps.model.LatLng
import dev.aplika.core.domain.model.LocalityGroup

data class MarkerState(
    val id: String,
    val localityGroup: LocalityGroup,
    @DrawableRes val iconResId: Int,
    val position: LatLng
)
