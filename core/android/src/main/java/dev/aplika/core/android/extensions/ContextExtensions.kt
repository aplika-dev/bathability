package dev.aplika.core.android.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Context.resourceToBitmap(@DrawableRes resId: Int): Bitmap {
    val vectorDrawable = checkNotNull(ContextCompat.getDrawable(this, resId))
    vectorDrawable.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)

    val bitmap = Bitmap.createBitmap(
        vectorDrawable.intrinsicWidth,
        vectorDrawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    vectorDrawable.draw(Canvas(bitmap))

    return bitmap
}