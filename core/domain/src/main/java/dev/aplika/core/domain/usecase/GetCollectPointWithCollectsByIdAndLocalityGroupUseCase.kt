package dev.aplika.core.domain.usecase

import dev.aplika.core.domain.model.CollectPointWithCollects
import dev.aplika.core.domain.model.LocalityGroup
import dev.aplika.core.domain.repository.CollectPointRepository
import dev.aplika.core.domain.repository.CollectRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.combine

@Singleton
class GetCollectPointWithCollectsByIdAndLocalityGroupUseCase @Inject constructor(
    private val collectPointRepository: CollectPointRepository,
    private val collectRepository: CollectRepository
) {

    operator fun invoke(id: String, localityGroup: LocalityGroup): Flow<CollectPointWithCollects?> =
        combine(
            collectPointRepository.getByIdAndLocalityGroup(id = id, localityGroup = localityGroup),
            collectRepository.getByIdAndLocalityGroup(id = id, localityGroup = localityGroup)
        ) { collectPoint, collects ->
            collectPoint?.let { CollectPointWithCollects(collectPoint = it, collects = collects) }
        }

}