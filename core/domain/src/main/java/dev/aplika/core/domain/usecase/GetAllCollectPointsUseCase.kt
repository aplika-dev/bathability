package dev.aplika.core.domain.usecase

import dev.aplika.core.domain.model.CollectPoint
import dev.aplika.core.domain.repository.CollectPointRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllCollectPointsUseCase @Inject constructor(
    private val collectPointRepository: CollectPointRepository
) {

    operator fun invoke(): Flow<List<CollectPoint>> {
        return collectPointRepository.getAll()
    }

}