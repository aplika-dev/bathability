package dev.aplika.core.navigation.destination

import dev.aplika.core.domain.model.LocalityGroup
import dev.aplika.core.navigation.Path

object CollectPointDetailsDestination : Destination(path = Path.COLLECT_POINT_DETAILS, args = arrayOf(Arguments.LOCALITY_GROUP, Arguments.ID)) {

    operator fun invoke(localityGroup: LocalityGroup, id: String) =
        routeBuilder()
            .appendEncodedPath(localityGroup.name)
            .appendEncodedPath(id)
            .build()
            .toString()

    object Arguments {
        const val ID = "id"
        const val LOCALITY_GROUP = "locality_group"
    }
}