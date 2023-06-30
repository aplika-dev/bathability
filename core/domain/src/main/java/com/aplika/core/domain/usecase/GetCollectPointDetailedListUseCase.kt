package com.aplika.core.domain.usecase

import com.aplika.core.domain.model.CollectPointDetailed
import com.aplika.core.domain.repository.CollectPointDetailedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCollectPointDetailedListUseCase @Inject constructor(
    private val collectPointDetailedRepository: CollectPointDetailedRepository
) {

    operator fun invoke(): Flow<List<CollectPointDetailed>> {
        return collectPointDetailedRepository.get()
    }

}