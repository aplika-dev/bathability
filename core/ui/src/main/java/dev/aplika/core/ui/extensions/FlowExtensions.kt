package dev.aplika.core.ui.extensions

import dev.aplika.core.ui.model.Task
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.transform

fun <T> Flow<T>.asTaskFlow(): Flow<Task<T>> =
    transform<T, Task<T>> { emit(Task.Success(it)) }
        .onStart { emit(Task.Loading) }
        .catch { throwable ->
            throwable.printStackTrace()
            FirebaseCrashlytics.getInstance().recordException(throwable)
            emit(Task.Error(throwable = throwable))
        }