package dev.aplika.core.ui.model

sealed class Task<out T> {
    object Loading : Task<Nothing>()
    class Success<T>(val data: T) : Task<T>()
    class Error<T>(val throwable: Throwable) : Task<T>()
}