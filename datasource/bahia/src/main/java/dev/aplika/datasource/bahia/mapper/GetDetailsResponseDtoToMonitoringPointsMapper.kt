package dev.aplika.datasource.bahia.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.BathabilitySituation
import dev.aplika.core.domain.model.MonitoringPoint
import dev.aplika.datasource.bahia.model.GetDetailsResponseDto
import javax.inject.Inject

class GetDetailsResponseDtoToMonitoringPointsMapper @Inject constructor() :
    Mapper<GetDetailsResponseDto, List<MonitoringPoint>> {
    override fun map(input: GetDetailsResponseDto): List<MonitoringPoint> {
        return input.cities
            .flatMap { city ->
                city.beaches.flatMap { beach ->
                    beach.locations.map { location ->
                        MonitoringPoint(
                            id = location.id.toString(),
                            latitude = location.latLng[0].toDouble(),
                            longitude = location.latLng[1].toDouble(),
                            bathabilitySituation = when (input.monitoringPoints.find { it.id == location.id }?.collects?.firstOrNull()?.category) {
                                1 -> BathabilitySituation.INAPPROPRIATE
                                2 -> BathabilitySituation.APPROPRIATE
                                else -> BathabilitySituation.UNDETERMINED
                            }
                        )
                    }
                }
            }
    }
}