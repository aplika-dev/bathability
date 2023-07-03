package com.aplika.feature.collect_point_details.ui

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
import androidx.compose.ui.res.painterResource
import com.aplika.feature.collect_point_details.state.CollectState

@Composable
internal fun CollectUI(
    state: CollectState
) {
    Column {
        ListItem(
            headlineContent = { Text(text = state.headline) },
            supportingContent = {
                Text(text = state.supporting)
            },
            leadingContent = {
                Icon(painter = painterResource(id = state.leadingIcon), contentDescription = "")
            },
            trailingContent = { Text(text = state.trailing) }
        )
        Divider()
    }
}