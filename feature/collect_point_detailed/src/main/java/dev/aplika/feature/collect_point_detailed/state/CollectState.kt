package dev.aplika.feature.collect_point_detailed.state

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import dev.aplika.core.kotlin.extensions.EMPTY
import dev.aplika.core.kotlin.extensions.ZERO

sealed interface CollectState {

    object IsLoading : CollectState

    data class HasContent(
        @DrawableRes val leadingIcon: Int,
        @StringRes val leadingContentDescription: Int,
        val headline: String,
        @StringRes val supportingResId: Int?,
        val trailingContent: Int?
    ) : CollectState
}

