package com.aplika.feature.map.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.aplika.feature.map.state.MarkerState
import com.google.android.gms.maps.model.BitmapDescriptor
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
        icon = bitmapDescriptorFromVector(context = LocalContext.current, resId = state.iconResId),
        onClick = {
            onMarkerClick(it)
            true
        }
    )
}

private fun bitmapDescriptorFromVector(
    context: Context,
    @DrawableRes resId: Int
): BitmapDescriptor {
    val vectorDrawable = checkNotNull(ContextCompat.getDrawable(context, resId))
    vectorDrawable.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)

    val bitmap = Bitmap.createBitmap(
        vectorDrawable.intrinsicWidth,
        vectorDrawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    vectorDrawable.draw(Canvas(bitmap))

    return BitmapDescriptorFactory.fromBitmap(bitmap)
}