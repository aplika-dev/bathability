package dev.aplika.core.domain.usecase

import dev.aplika.core.domain.model.CollectPoint
import dev.aplika.core.domain.repository.BeachCollectRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetBeachCollectsUseCase @Inject constructor(
    private val beachCollectRepository: BeachCollectRepository
) {

    operator fun invoke(): Flow<List<CollectPoint>> {
        return beachCollectRepository.getAll()
    }

}