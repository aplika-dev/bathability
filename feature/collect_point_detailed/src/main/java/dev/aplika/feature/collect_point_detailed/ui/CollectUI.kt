package dev.aplika.feature.collect_point_detailed.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.aplika.feature.collect_point_detailed.state.CollectState
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import dev.aplika.core.kotlin.extensions.EMPTY

@Composable
internal fun CollectUI(
    state: CollectState
) {
    when (state) {
        is CollectState.HasContent ->
            ListItem(
                headlineContent = {
                    Text(text = state.headline)
                },
                supportingContent = {
                    state.supportingResId?.let {
                        Text(
                            text = stringResource(id = it),
                            fontWeight = FontWeight.Light
                        )
                    }
                },
                leadingContent = {
                    Icon(
                        painter = painterResource(id = state.leadingIcon),
                        contentDescription = stringResource(id = state.leadingContentDescription)
                    )
                },
                trailingContent = {
                    state.trailingContent?.let {
                        Text(
                            text = stringResource(id = it),
                            fontWeight = FontWeight.Light
                        )
                    }
                }
            )
        CollectState.IsLoading ->
            ListItem(
                headlineContent = {
                    Text(
                        modifier = Modifier.placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.shimmer()
                        ),
                        text = String.EMPTY
                    )
                },
                supportingContent = {
                    Text(
                        modifier = Modifier.placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.shimmer()
                        ),
                        text = String.EMPTY,
                        fontWeight = FontWeight.Light
                    )
                },
                leadingContent = {
                    Box(
                        modifier = Modifier
                            .size(size = 16.dp)
                            .placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.shimmer()
                        )
                    )
                },
                trailingContent = {
                    Text(
                        modifier = Modifier.placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.shimmer()
                        ),
                        text = String.EMPTY,
                        fontWeight = FontWeight.Light
                    )
                }
            )
    }

    Divider()
}