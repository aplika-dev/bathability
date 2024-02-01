package dev.aplika.feature.map.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.feature.map.MapUIState
import dev.aplika.core.resources.R
import dev.aplika.feature.map.state.MarkerState
import com.google.android.gms.maps.model.LatLng
import dev.aplika.core.domain.model.BathabilityStatus
import dev.aplika.core.domain.model.CollectPoint
import javax.inject.Inject

class CollectPointsToUIStateMapper @Inject constructor() : Mapper<List<CollectPoint>, MapUIState> {
    override fun map(input: List<CollectPoint>): MapUIState {
        return MapUIState(
            isLoading = false,
            locationList = input.map { collectPoint ->
                MarkerState(
                    id = collectPoint.id,
                    iconResId = when (collectPoint.latestBathabilityStatus) {
                        BathabilityStatus.APPROPRIATE -> R.drawable.ic_marker_happy
                        BathabilityStatus.INAPPROPRIATE -> R.drawable.ic_marker_sad
                        BathabilityStatus.UNDETERMINED -> R.drawable.ic_marker_unknown
                    },
                    position = LatLng(collectPoint.latitude, collectPoint.longitude)
                )
            }
        )
    }
}