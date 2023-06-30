package com.aplika.feature.map.mapper

import com.aplika.core.android.mapper.Mapper
import com.aplika.core.domain.model.CollectPoint
import com.aplika.feature.map.presentation.MarkerPresentation
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class CollectPointToMarkerPresentationMapper @Inject constructor() : Mapper<CollectPoint, MarkerPresentation> {
    override fun map(input: CollectPoint): MarkerPresentation {
        return MarkerPresentation(
            id = input.id,
            color = when (input.isBathable) {
                true -> BitmapDescriptorFactory.HUE_BLUE
                false -> BitmapDescriptorFactory.HUE_RED
                null -> BitmapDescriptorFactory.HUE_CYAN
            },
            position = LatLng(input.latitude, input.longitude)
        )
    }
}