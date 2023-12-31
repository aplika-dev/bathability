package dev.aplika.core.domain.usecase

import dev.aplika.core.domain.repository.BeachCollectRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SyncBeachCollectsUseCase @Inject constructor(
    private val beachCollectRepository: BeachCollectRepository
) {

    operator fun invoke(): Flow<Unit> {
        return beachCollectRepository.sync()
    }

}