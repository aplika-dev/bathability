package dev.aplika.core.domain.usecase

import dev.aplika.core.domain.repository.CollectPointRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchAllCollectPointsUseCase @Inject constructor(
    private val collectPointRepository: CollectPointRepository
) {

    operator fun invoke(): Flow<Unit> {
        return collectPointRepository.fetch()
    }

}