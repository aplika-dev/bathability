package dev.aplika.feature.collect_point_detailed.ui

import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import dev.aplika.feature.collect_point_detailed.state.CollectState
import dev.aplika.core.resources.R

@Composable
internal fun CollectUI(
    state: CollectState
) {
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
                    text = stringResource(id = R.string.escherichia_coli_factor_mask, formatArgs = arrayOf(it)),
                    fontWeight = FontWeight.Light
                )
            }
        }
    )
    Divider()
}