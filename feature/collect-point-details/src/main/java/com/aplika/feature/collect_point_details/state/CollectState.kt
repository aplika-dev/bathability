package com.aplika.feature.collect_point_details.state

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CollectState(
    @DrawableRes val leadingIcon: Int,
    @StringRes val leadingContentDescription: Int,
    val headline: String,
    @StringRes val supportingResId: Int,
    val trailing: String
)
