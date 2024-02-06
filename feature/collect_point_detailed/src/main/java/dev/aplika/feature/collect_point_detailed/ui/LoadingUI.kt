package dev.aplika.feature.collect_point_detailed.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material3.placeholder
import com.google.accompanist.placeholder.material3.shimmer

@Composable
internal fun LoadingUI() {
    Box(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(top = 24.dp)
            .fillMaxWidth(fraction = 0.7f)
            .placeholder(
                visible = true,
                highlight = PlaceholderHighlight.shimmer()
            )
    )
    Box(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .padding(horizontal = 24.dp)
            .fillMaxWidth(fraction = 0.3f)
            .placeholder(
                visible = true,
                highlight = PlaceholderHighlight.shimmer()
            )
    )

    repeat(3) {
        ListItem(
            headlineContent = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.4f)
                        .placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.shimmer()
                        )
                )
            },
            supportingContent = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.2f)
                        .placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.shimmer()
                        )
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.5f)
                        .placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.shimmer()
                        )
                )
            }
        )
        Divider()
    }
}