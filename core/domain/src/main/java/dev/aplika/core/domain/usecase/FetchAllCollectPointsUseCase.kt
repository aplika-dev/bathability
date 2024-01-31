package dev.aplika.core.domain.usecase

import dev.aplika.core.domain.repository.SantaCatarinaCollectPointRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchAllCollectPointsUseCase @Inject constructor(
    private val santaCatarinaCollectPointRepository: SantaCatarinaCollectPointRepository
) {

    operator fun invoke(): Flow<Unit> {
        return santaCatarinaCollectPointRepository.fetch()
    }

}