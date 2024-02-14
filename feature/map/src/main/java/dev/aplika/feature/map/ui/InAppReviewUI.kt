package dev.aplika.feature.map.ui

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.android.play.core.ktx.launchReview
import com.google.android.play.core.ktx.requestReview
import com.google.android.play.core.review.ReviewManagerFactory
import dev.aplika.core.android.extensions.monitor
import kotlinx.coroutines.delay

@Composable
internal fun InAppReviewUI() {
    val context = LocalContext.current

    val reviewManager = remember { ReviewManagerFactory.create(context) }

    LaunchedEffect(key1 = reviewManager) {
        try {
            reviewManager.launchReview(context as Activity, reviewManager.requestReview())
        } catch (throwable: Throwable) {
            throwable.monitor()
        }
    }
}