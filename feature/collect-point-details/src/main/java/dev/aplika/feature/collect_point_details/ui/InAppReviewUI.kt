package dev.aplika.feature.collect_point_details.ui

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.google.android.play.core.ktx.launchReview
import com.google.android.play.core.ktx.requestReview
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import kotlinx.coroutines.delay

private const val DELAY_TO_REQUEST_REVIEW_IN_MILLIS = 5000L

@Composable
internal fun InAppReviewUI() {
    val context = LocalContext.current

    val reviewManager = remember { ReviewManagerFactory.create(context) }

    LaunchedEffect(key1 = reviewManager) {
        delay(DELAY_TO_REQUEST_REVIEW_IN_MILLIS)
        val reviewInfo = reviewManager.requestReview()
        reviewManager.launchReview(context as Activity, reviewInfo)
    }
}