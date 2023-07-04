package dev.aplika.core.navigation.destination

import android.net.Uri
import dev.aplika.core.navigation.extensions.toPath

abstract class Destination(protected val path: String, vararg args: String) {

    protected fun routeBuilder(): Uri.Builder =
        Uri.Builder()
            .appendEncodedPath(path)

    val route: String =
        routeBuilder()
            .apply { args.forEach { appendEncodedPath(it.toPath()) } }
            .build()
            .toString()

}