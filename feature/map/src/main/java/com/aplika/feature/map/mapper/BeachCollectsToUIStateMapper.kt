package com.aplika.feature.map.mapper

import com.aplika.core.android.mapper.Mapper
import com.aplika.core.domain.model.BathabilitySituation
import com.aplika.core.domain.model.BeachCollect
import com.aplika.feature.map.MapUIState
import com.aplika.feature.map.state.MarkerState
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class BeachCollectsToUIStateMapper @Inject constructor() : Mapper<List<BeachCollect>, MapUIState> {
    override fun map(input: List<BeachCollect>): MapUIState {
        return MapUIState(
            isLoading = false,
            locationList = input.map { beachCollect ->
                MarkerState(
                    id = beachCollect.id,
                    color = when (beachCollect.collects.firstOrNull()?.bathabilitySituation) {
                        BathabilitySituation.APPROPRIATE -> BitmapDescriptorFactory.HUE_BLUE
                        BathabilitySituation.INAPPROPRIATE -> BitmapDescriptorFactory.HUE_RED
                        BathabilitySituation.UNDETERMINED -> BitmapDescriptorFactory.HUE_AZURE
                        null -> BitmapDescriptorFactory.HUE_AZURE
                    },
                    position = LatLng(beachCollect.latitude, beachCollect.longitude)
                )
            }
        )
    }
}