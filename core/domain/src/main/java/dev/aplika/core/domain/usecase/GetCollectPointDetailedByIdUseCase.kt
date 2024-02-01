package dev.aplika.core.domain.usecase

import dev.aplika.core.domain.model.CollectPointDetailed
import dev.aplika.core.domain.model.CollectPointId
import dev.aplika.core.domain.repository.CollectPointDetailedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCollectPointDetailedByIdUseCase @Inject constructor(
    private val collectPointDetailedRepository: CollectPointDetailedRepository
) {

    operator fun invoke(id: CollectPointId): Flow<CollectPointDetailed> =
        collectPointDetailedRepository.getById(id = id)

}