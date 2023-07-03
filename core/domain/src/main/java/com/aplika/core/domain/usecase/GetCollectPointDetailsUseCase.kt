package com.aplika.core.domain.usecase

import com.aplika.core.domain.model.BeachCollect
import com.aplika.core.domain.repository.BeachCollectRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCollectPointDetailsUseCase @Inject constructor(
    private val beachCollectRepository: BeachCollectRepository
) {

    operator fun invoke(id: String): Flow<BeachCollect> =
        beachCollectRepository.getById(id = id)

}