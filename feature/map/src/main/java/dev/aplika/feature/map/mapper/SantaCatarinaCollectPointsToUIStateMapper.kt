package dev.aplika.feature.map.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.santa_catarina.SantaCatarinaBathabilityStatus
import dev.aplika.core.domain.model.santa_catarina.SantaCatarinaCollectPoint
import dev.aplika.feature.map.MapUIState
import dev.aplika.core.resources.R
import dev.aplika.feature.map.state.MarkerState
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class SantaCatarinaCollectPointsToUIStateMapper @Inject constructor() : Mapper<List<SantaCatarinaCollectPoint>, MapUIState> {
    override fun map(input: List<SantaCatarinaCollectPoint>): MapUIState {
        return MapUIState(
            isLoading = false,
            locationList = input.map { beachCollect ->
                MarkerState(
                    id = beachCollect.id,
                    iconResId = when (beachCollect.collects.firstOrNull()?.bathabilityStatus) {
                        SantaCatarinaBathabilityStatus.APPROPRIATE -> R.drawable.ic_marker_happy
                        SantaCatarinaBathabilityStatus.INAPPROPRIATE -> R.drawable.ic_marker_sad
                        SantaCatarinaBathabilityStatus.UNDETERMINED -> R.drawable.ic_marker_unknown
                        null -> R.drawable.ic_marker_unknown
                    },
                    position = LatLng(beachCollect.latitude, beachCollect.longitude)
                )
            }
        )
    }
}