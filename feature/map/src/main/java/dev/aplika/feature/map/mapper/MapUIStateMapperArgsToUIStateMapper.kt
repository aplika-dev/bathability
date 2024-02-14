package dev.aplika.feature.map.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.feature.map.MapUIState
import dev.aplika.core.resources.R
import dev.aplika.feature.map.state.MarkerState
import com.google.android.gms.maps.model.LatLng
import dev.aplika.core.domain.model.BathabilityStatus
import dev.aplika.core.domain.model.CollectPoint
import dev.aplika.feature.map.model.MapUIStateMapperArgs
import javax.inject.Inject

class MapUIStateMapperArgsToUIStateMapper @Inject constructor() : Mapper<MapUIStateMapperArgs, MapUIState> {
    override fun map(input: MapUIStateMapperArgs): MapUIState {
        return MapUIState(
            isLoading = false,
            locationList = input.collectPoints.map { collectPoint ->
                MarkerState(
                    id = collectPoint.id,
                    localityGroup = collectPoint.localityGroup,
                    iconResId = when (collectPoint.latestBathabilityStatus) {
                        BathabilityStatus.APPROPRIATE -> R.drawable.ic_marker_happy
                        BathabilityStatus.INAPPROPRIATE -> R.drawable.ic_marker_sad
                        BathabilityStatus.UNDETERMINED -> R.drawable.ic_marker_unknown
                    },
                    position = LatLng(collectPoint.latitude, collectPoint.longitude)
                )
            },
            shouldShowInAppReview = input.shouldShowInAppReview
        )
    }
}