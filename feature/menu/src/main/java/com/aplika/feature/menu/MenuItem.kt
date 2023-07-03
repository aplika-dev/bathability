package com.aplika.feature.menu

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.aplika.core.navigation.destination.AboutDestination
import com.aplika.core.navigation.destination.Destination
import com.aplika.core.navigation.destination.MapDestination
import com.aplika.core.resources.R

enum class MenuItem(
    @DrawableRes val iconResId: Int,
    @StringRes val iconContentDescriptionResId: Int,
    @StringRes val labelResId: Int,
    val destination: Destination
) {
    MAP(
        iconResId = R.drawable.ic_map,
        iconContentDescriptionResId = R.string.ic_map_content_description,
        labelResId = R.string.map,
        destination = MapDestination
    ),
    ABOUT(
        iconResId = R.drawable.ic_info,
        iconContentDescriptionResId = R.string.ic_info_content_description,
        labelResId = R.string.about_the_app,
        destination = AboutDestination
    )
}