package com.aplika.core.domain.usecase

import com.aplika.core.domain.model.CollectPoint
import com.aplika.core.domain.repository.BeachRepository
import com.aplika.core.domain.repository.CityRepository
import com.aplika.core.domain.repository.CollectPointRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(ExperimentalCoroutinesApi::class)
@Singleton
class GetCollectPointListUseCase @Inject constructor(
    private val cityRepository: CityRepository,
    private val beachRepository: BeachRepository,
    private val collectPointRepository: CollectPointRepository
) {

    operator fun invoke(): Flow<List<CollectPoint>> {
        return cityRepository.get()
            .flatMapLatest { cityList ->
                combine(cityList.map { beachRepository.getByCityId(cityId = it.id) }) { it.asList().flatten() }
            }
            .flatMapLatest { beachList ->
                combine(beachList.map { collectPointRepository.getByBeachId(beachId = it.id) }) { it.asList().flatten() }
            }
    }

}