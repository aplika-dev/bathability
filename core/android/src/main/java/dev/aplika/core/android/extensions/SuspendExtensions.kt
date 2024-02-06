package dev.aplika.core.android.extensions

import kotlin.coroutines.cancellation.CancellationException

suspend fun <T> suspendRunCatching(block: suspend () -> T): T? = try {
    block()
} catch (cancellationException: CancellationException) {
    throw cancellationException
} catch (exception: Exception) {
    exception.monitor()
    null
}