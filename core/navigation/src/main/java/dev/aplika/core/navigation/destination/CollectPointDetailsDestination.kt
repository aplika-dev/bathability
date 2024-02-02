package dev.aplika.core.navigation.destination

import dev.aplika.core.domain.model.CollectPointId
import dev.aplika.core.navigation.Path

object CollectPointDetailsDestination : Destination(path = Path.COLLECT_POINT_DETAILS, args = arrayOf(Arguments.LOCALITY_GROUP, Arguments.ID)) {

    operator fun invoke(id: CollectPointId) =
        routeBuilder()
            .appendEncodedPath(id.localityGroup.name)
            .appendEncodedPath(id.id)
            .build()
            .toString()

    object Arguments {
        const val ID = "id"
        const val LOCALITY_GROUP = "locality_group"
    }
}