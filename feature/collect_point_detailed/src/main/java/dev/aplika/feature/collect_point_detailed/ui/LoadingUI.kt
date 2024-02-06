package dev.aplika.feature.collect_point_detailed.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material3.placeholder
import com.google.accompanist.placeholder.material3.shimmer
import dev.aplika.core.kotlin.extensions.EMPTY

@Composable
internal fun LoadingUI() {
    Column {
        Text(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(top = 24.dp)
                .fillMaxWidth(fraction = 0.7f)
                .placeholder(
                    visible = true,
                    highlight = PlaceholderHighlight.shimmer()
                ),
            text = String.EMPTY
        )
        Text(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .padding(horizontal = 24.dp)
                .fillMaxWidth(fraction = 0.3f)
                .placeholder(
                    visible = true,
                    highlight = PlaceholderHighlight.shimmer()
                ),
            text = String.EMPTY
        )

        repeat(3) {
            ListItem(
                headlineContent = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(fraction = 0.4f)
                            .placeholder(
                                visible = true,
                                highlight = PlaceholderHighlight.shimmer()
                            ),
                        text = String.EMPTY
                    )
                },
                supportingContent = {
                    Text(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .fillMaxWidth(fraction = 0.2f)
                            .placeholder(
                                visible = true,
                                highlight = PlaceholderHighlight.shimmer()
                            ),
                        text = String.EMPTY
                    )
                },
                leadingContent = {
                    Box(
                        modifier = Modifier
                            .size(size = 32.dp)
                            .placeholder(
                                visible = true,
                                highlight = PlaceholderHighlight.shimmer()
                            )
                    )
                },
                trailingContent = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(fraction = 0.1f)
                            .placeholder(
                                visible = true,
                                highlight = PlaceholderHighlight.shimmer()
                            ),
                        text = String.EMPTY
                    )
                }
            )
            Divider()
        }
    }
}