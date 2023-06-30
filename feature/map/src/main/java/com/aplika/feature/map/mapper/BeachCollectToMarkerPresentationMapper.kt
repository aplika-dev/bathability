package com.aplika.feature.map.mapper

import com.aplika.core.android.mapper.Mapper
import com.aplika.core.domain.model.BathabilitySituation
import com.aplika.core.domain.model.BeachCollect
import com.aplika.core.kotlin.extensions.formatToString
import com.aplika.feature.map.presentation.CollectPresentation
import com.aplika.feature.map.presentation.MarkerWindowPresentation
import com.aplika.feature.map.presentation.MarkerPresentation
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class BeachCollectToMarkerPresentationMapper @Inject constructor() : Mapper<BeachCollect, MarkerPresentation> {
    override fun map(input: BeachCollect): MarkerPresentation {
        return MarkerPresentation(
            id = input.id,
            color = when (input.collects.firstOrNull()?.bathabilitySituation) {
                BathabilitySituation.APPROPRIATE -> BitmapDescriptorFactory.HUE_BLUE
                BathabilitySituation.INAPPROPRIATE -> BitmapDescriptorFactory.HUE_RED
                BathabilitySituation.UNDETERMINED -> BitmapDescriptorFactory.HUE_AZURE
                null -> BitmapDescriptorFactory.HUE_AZURE
            },
            position = LatLng(input.latitude, input.longitude),
            windowPresentation = MarkerWindowPresentation(
                title = input.beach,
                description = "${input.collectPoint} - ${input.location}",
                collects = input.collects.map {
                    CollectPresentation(
                        date = it.date.formatToString(format = "dd/MM/yyyy"),
                        bathableSituation = it.bathabilitySituation.id.orEmpty(),
                        rainSituation = it.rainSituation.id.orEmpty(),
                        escherichiaColiFactor = it.escherichiaColiFactor.toString()
                    )
                }
            )
        )
    }
}