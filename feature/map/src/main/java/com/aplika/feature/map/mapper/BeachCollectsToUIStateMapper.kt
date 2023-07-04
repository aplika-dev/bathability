package com.aplika.feature.map.mapper

import com.aplika.core.android.mapper.Mapper
import com.aplika.core.domain.model.BathabilitySituation
import com.aplika.core.domain.model.BeachCollect
import com.aplika.feature.map.MapUIState
import com.aplika.core.resources.R
import com.aplika.feature.map.state.MarkerState
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class BeachCollectsToUIStateMapper @Inject constructor() : Mapper<List<BeachCollect>, MapUIState> {
    override fun map(input: List<BeachCollect>): MapUIState {
        return MapUIState(
            isLoading = false,
            locationList = input.map { beachCollect ->
                MarkerState(
                    id = beachCollect.id,
                    iconResId = when (beachCollect.collects.firstOrNull()?.bathabilitySituation) {
                        BathabilitySituation.APPROPRIATE -> R.drawable.ic_marker_happy
                        BathabilitySituation.INAPPROPRIATE -> R.drawable.ic_marker_sad
                        BathabilitySituation.UNDETERMINED -> R.drawable.ic_marker_unknown
                        null -> R.drawable.ic_marker_unknown
                    },
                    position = LatLng(beachCollect.latitude, beachCollect.longitude)
                )
            }
        )
    }
}