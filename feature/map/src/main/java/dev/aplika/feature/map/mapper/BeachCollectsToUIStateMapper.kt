package dev.aplika.feature.map.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.BathabilityStatus
import dev.aplika.core.domain.model.CollectPoint
import dev.aplika.feature.map.MapUIState
import dev.aplika.core.resources.R
import dev.aplika.feature.map.state.MarkerState
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class BeachCollectsToUIStateMapper @Inject constructor() : Mapper<List<CollectPoint>, MapUIState> {
    override fun map(input: List<CollectPoint>): MapUIState {
        return MapUIState(
            isLoading = false,
            locationList = input.map { beachCollect ->
                MarkerState(
                    id = beachCollect.id,
                    iconResId = when (beachCollect.collects.firstOrNull()?.bathabilityStatus) {
                        BathabilityStatus.APPROPRIATE -> R.drawable.ic_marker_happy
                        BathabilityStatus.INAPPROPRIATE -> R.drawable.ic_marker_sad
                        BathabilityStatus.UNDETERMINED -> R.drawable.ic_marker_unknown
                        null -> R.drawable.ic_marker_unknown
                    },
                    position = LatLng(beachCollect.latitude, beachCollect.longitude)
                )
            }
        )
    }
}