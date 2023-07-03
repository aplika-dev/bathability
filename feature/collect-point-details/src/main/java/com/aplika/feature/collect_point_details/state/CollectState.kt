package com.aplika.feature.collect_point_details.state

import androidx.annotation.DrawableRes

data class CollectState(
    @DrawableRes val leadingIcon: Int,
    val headline: String,
    val supporting: String,
    val trailing: String
)
