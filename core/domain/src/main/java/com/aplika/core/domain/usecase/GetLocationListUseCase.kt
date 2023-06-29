package com.aplika.core.domain.usecase

import com.aplika.core.domain.model.Location
import com.aplika.core.domain.repository.BeachRepository
import com.aplika.core.domain.repository.CityRepository
import com.aplika.core.domain.repository.LocationRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(ExperimentalCoroutinesApi::class)
@Singleton
class GetLocationListUseCase @Inject constructor(
    private val cityRepository: CityRepository,
    private val beachRepository: BeachRepository,
    private val locationRepository: LocationRepository
) {

    operator fun invoke(): Flow<List<Location>> {
        return cityRepository.get()
            .flatMapLatest { cityList ->
                combine(cityList.map { beachRepository.getByCityId(cityId = it.id) }) { it.asList().flatten() }
            }
            .flatMapLatest { beachList ->
                combine(beachList.map { locationRepository.getByBeachId(beachId = it.id) }) { it.asList().flatten() }
            }
    }

}