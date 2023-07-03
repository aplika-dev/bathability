package com.aplika.feature.collect_point_details.state

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.aplika.core.kotlin.extensions.EMPTY
import com.aplika.core.kotlin.extensions.ZERO

data class CollectState(
    val isLoading: Boolean = true,
    @DrawableRes val leadingIcon: Int = Int.ZERO,
    @StringRes val leadingContentDescription: Int = Int.ZERO,
    val headline: String = String.EMPTY,
    @StringRes val supportingResId: Int = Int.ZERO,
    val trailingContent: Int = Int.ZERO
)
