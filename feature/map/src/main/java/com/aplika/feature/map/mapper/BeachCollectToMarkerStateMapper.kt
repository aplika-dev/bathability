package com.aplika.feature.map.mapper

import com.aplika.core.android.mapper.Mapper
import com.aplika.core.domain.model.BathabilitySituation
import com.aplika.core.domain.model.BeachCollect
import com.aplika.core.kotlin.extensions.formatToString
import com.aplika.feature.map.state.MarkerState
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class BeachCollectToMarkerStateMapper @Inject constructor() : Mapper<BeachCollect, MarkerState> {
    override fun map(input: BeachCollect): MarkerState {
        return MarkerState(
            id = input.id,
            color = when (input.collects.firstOrNull()?.bathabilitySituation) {
                BathabilitySituation.APPROPRIATE -> BitmapDescriptorFactory.HUE_BLUE
                BathabilitySituation.INAPPROPRIATE -> BitmapDescriptorFactory.HUE_RED
                BathabilitySituation.UNDETERMINED -> BitmapDescriptorFactory.HUE_AZURE
                null -> BitmapDescriptorFactory.HUE_AZURE
            },
            position = LatLng(input.latitude, input.longitude)
        )
    }
}