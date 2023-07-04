package dev.aplika.core.navigation.extensions

internal fun String.toPath() =
    PATH_BEGIN_DELIMITER + this + PATH_END_DELIMITER

private const val PATH_BEGIN_DELIMITER = "{"
private const val PATH_END_DELIMITER = "}"