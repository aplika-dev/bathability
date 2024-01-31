package dev.aplika.core.domain.usecase

import dev.aplika.core.domain.model.santa_catarina.SantaCatarinaCollectPoint
import dev.aplika.core.domain.repository.SantaCatarinaCollectPointRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllCollectPointsUseCase @Inject constructor(
    private val repository: SantaCatarinaCollectPointRepository
) {

    operator fun invoke(): Flow<List<SantaCatarinaCollectPoint>> {
        return repository.getAll()
    }

}