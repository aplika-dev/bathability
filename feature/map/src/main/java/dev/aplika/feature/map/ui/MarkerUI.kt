package dev.aplika.feature.map.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import dev.aplika.core.android.extensions.resourceToBitmap
import dev.aplika.feature.map.state.MarkerState
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberMarkerState

@Composable
internal fun MarkerUI(
    state: MarkerState,
    onMarkerClick: (marker: Marker) -> Unit
) {
    val option = BitmapFactory.Options()
    option.inPreferredConfig = Bitmap.Config.ARGB_8888
    Marker(
        state = rememberMarkerState(position = state.position),
        icon = BitmapDescriptorFactory.fromBitmap(LocalContext.current.resourceToBitmap(resId = state.iconResId)),
        onClick = {
            onMarkerClick(it)
            true
        }
    )
}