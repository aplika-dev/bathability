package com.aplika.core.data.repository

import com.aplika.core.android.di.DefaultDispatcher
import com.aplika.core.android.di.IoDispatcher
import com.aplika.core.data.mapper.CityBeachCollectPointToCollectPointDetailedMapper
import com.aplika.core.domain.model.CollectPointDetailed
import com.aplika.core.domain.repository.BeachRepository
import com.aplika.core.domain.repository.CityRepository
import com.aplika.core.domain.repository.CollectPointDetailedRepository
import com.aplika.core.domain.repository.CollectPointRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(ExperimentalCoroutinesApi::class)
@Singleton
class CollectPointDetailedRepositoryImpl @Inject constructor(
    private val cityRepository: CityRepository,
    private val beachRepository: BeachRepository,
    private val collectPointRepository: CollectPointRepository,
    private val cityBeachCollectPointToCollectPointDetailedMapper: CityBeachCollectPointToCollectPointDetailedMapper,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : CollectPointDetailedRepository {
    override fun get(): Flow<List<CollectPointDetailed>> {
        return cityRepository.get()
            .flatMapLatest { cityList ->
                combine(cityList.map { city ->
                    beachRepository.getByCityId(cityId = city.id).map { beachList -> beachList.map { it to city } }
                }) { it.asList().flatten() }
            }
            .flatMapLatest { beachWithCityList ->
                combine(beachWithCityList.map { (beach, city) ->
                    collectPointRepository.getByBeachId(beachId = beach.id) .map { collectPointList -> collectPointList.map { Triple(city, beach, it) } }
                }) { it.asList().flatten() }
            }
            .map { list -> list.map { cityBeachCollectPointToCollectPointDetailedMapper.map(input = it) } }
            .flowOn(defaultDispatcher)
    }
}