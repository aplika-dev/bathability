package com.aplika.feature.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aplika.core.resources.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutUI(
    navController: NavController,
    versionName: String
) {
    val uriHandler = LocalUriHandler.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.about_the_app)) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = stringResource(id = R.string.ic_arrow_back_content_description)
                        )
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it)
        ) {
            Column(
                modifier = Modifier.align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.6f)
                        .padding(top = 24.dp),
                    painter = painterResource(id = R.drawable.ic_aplika_white),
                    contentDescription = stringResource(id = R.string.ic_aplika_white_content_description),
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground)
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.developed_by_aplika),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            FloatingActionButton(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .align(Alignment.BottomEnd),
                onClick = { uriHandler.openUri(WHATSAPP_LINK) }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_whatsapp),
                    contentDescription = stringResource(id = R.string.ic_whatsapp_content_description)
                )
            }

            Text(
                modifier = Modifier.align(Alignment.BottomStart).padding(all = 16.dp),
                text = stringResource(id = R.string.app_version_mask, formatArgs = arrayOf(versionName)),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

private const val WHATSAPP_LINK = "https://api.whatsapp.com/send?phone=5551981835577"