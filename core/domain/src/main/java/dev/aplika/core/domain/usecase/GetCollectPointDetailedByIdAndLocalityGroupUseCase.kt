package dev.aplika.core.domain.usecase

import dev.aplika.core.domain.model.CollectPointDetailed
import dev.aplika.core.domain.model.LocalityGroup
import dev.aplika.core.domain.repository.CollectPointDetailedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCollectPointDetailedByIdAndLocalityGroupUseCase @Inject constructor(
    private val collectPointDetailedRepository: CollectPointDetailedRepository
) {

    operator fun invoke(id: String, localityGroup: LocalityGroup): Flow<CollectPointDetailed> =
        collectPointDetailedRepository.getByIdAndLocalityGroup(id = id, localityGroup = localityGroup)

}