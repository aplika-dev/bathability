package com.aplika.feature.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aplika.core.resources.R

@Composable
fun AboutUI() {
    Scaffold {
        Column(
            modifier = Modifier.padding(paddingValues = it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.8f)
                    .padding(top = 24.dp),
                painter = painterResource(id = R.drawable.ic_aplika_white),
                contentDescription = stringResource(id = R.string.ic_aplika_white_content_description),
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground)
            )
            Text(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.developed_by_aplika),
                style = MaterialTheme.typography.titleMedium
            )
            val uriHandler = LocalUriHandler.current

            FilledIconButton(
                modifier = Modifier.padding(top = 16.dp),
                onClick = { uriHandler.openUri(WHATSAPP_LINK) }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_whatsapp),
                    contentDescription = stringResource(id = R.string.ic_whatsapp_content_description)
                )
            }
        }
    }
}

private const val WHATSAPP_LINK = "https://api.whatsapp.com/send?phone=5551981835577"