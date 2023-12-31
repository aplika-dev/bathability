package dev.aplika.core.navigation.destination

import dev.aplika.core.navigation.Path

object CollectPointDetailsDestination : Destination(path = Path.COLLECT_POINT_DETAILS, args = arrayOf(Arguments.ID)) {

    operator fun invoke(id: String) =
        routeBuilder()
            .appendEncodedPath(id)
            .build()
            .toString()

    object Arguments {
        const val ID = "id"
    }
}