package dev.aplika.core.domain.usecase

import dev.aplika.core.domain.model.MonitoringPoint
import dev.aplika.core.domain.repository.MonitoringPointRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMonitoringPointDetailsUseCase @Inject constructor(
    private val monitoringPointRepository: MonitoringPointRepository
) {

    operator fun invoke(id: String): Flow<MonitoringPoint> {
        TODO()
    }

}