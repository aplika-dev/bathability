package com.aplika.core.domain.usecase

import com.aplika.core.domain.model.BeachCollect
import com.aplika.core.domain.repository.BeachCollectRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetBeachCollectsUseCase @Inject constructor(
    private val beachCollectRepository: BeachCollectRepository
) {

    operator fun invoke(): Flow<List<BeachCollect>> {
        return beachCollectRepository.getAll()
    }

}