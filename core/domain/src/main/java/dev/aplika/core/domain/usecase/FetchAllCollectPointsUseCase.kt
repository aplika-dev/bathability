package dev.aplika.core.domain.usecase

import dev.aplika.core.domain.repository.CollectPointRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchAllCollectPointsUseCase @Inject constructor(
    private val collectPointRepository: CollectPointRepository
) {

    suspend operator fun invoke() {
        collectPointRepository.fetchAllCatching()
    }

}