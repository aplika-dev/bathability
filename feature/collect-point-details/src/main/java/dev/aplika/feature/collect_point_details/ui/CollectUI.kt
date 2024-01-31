package dev.aplika.feature.collect_point_details.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import dev.aplika.core.resources.R
import dev.aplika.feature.collect_point_details.state.CollectState
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

@Composable
internal fun CollectUI(
    state: CollectState
) {
    Column {
        ListItem(
            headlineContent = {
                Text(
                    modifier = Modifier.placeholder(
                        visible = state.isLoading,
                        highlight = PlaceholderHighlight.shimmer()
                    ),
                    text = state.headline
                )
            },
            supportingContent = {
                Text(
                    modifier = Modifier.placeholder(
                        visible = state.isLoading,
                        highlight = PlaceholderHighlight.shimmer()
                    ),
                    text = stringResource(id = state.supportingResId),
                    fontWeight = FontWeight.Light
                )
            },
            leadingContent = {
                Icon(
                    modifier = Modifier.placeholder(
                        visible = state.isLoading,
                        highlight = PlaceholderHighlight.shimmer()
                    ),
                    painter = painterResource(id = state.leadingIcon),
                    contentDescription = stringResource(id = state.leadingContentDescription)
                )
            },
            trailingContent = {
                Text(
                    modifier = Modifier.placeholder(
                        visible = state.isLoading,
                        highlight = PlaceholderHighlight.shimmer()
                    ),
                    text = stringResource(id = R.string.escherichia_coli_factor_mask, formatArgs = arrayOf(state.trailingContent)),
                    fontWeight = FontWeight.Light
                )
            }
        )
        Divider()
    }
}