package com.aplika.feature.map.mapper

import com.aplika.core.android.mapper.Mapper
import com.aplika.core.domain.model.CollectPoint
import com.aplika.core.domain.model.CollectPointDetailed
import com.aplika.feature.map.presentation.MarkerInfoPresentation
import com.aplika.feature.map.presentation.MarkerPresentation
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class CollectPointDetailedToMarkerPresentationMapper @Inject constructor() : Mapper<CollectPointDetailed, MarkerPresentation> {
    override fun map(input: CollectPointDetailed): MarkerPresentation {
        return MarkerPresentation(
            id = input.id,
            color = when (input.isBathable) {
                true -> BitmapDescriptorFactory.HUE_BLUE
                false -> BitmapDescriptorFactory.HUE_RED
                null -> BitmapDescriptorFactory.HUE_CYAN
            },
            position = LatLng(input.latitude, input.longitude),
            info = MarkerInfoPresentation(
                title = "${input.beach.name} - ${input.city.name}",
                description = "${input.name} - ${input.description}"
            )
        )
    }
}