package com.aplika.core.domain.usecase

import com.aplika.core.domain.model.Beach
import com.aplika.core.domain.model.City
import com.aplika.core.domain.repository.BeachRepository
import com.aplika.core.domain.repository.CityRepository
import com.aplika.core.domain.repository.LocationRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SyncLocationsUseCase @Inject constructor(
    private val cityRepository: CityRepository,
    private val beachRepository: BeachRepository,
    private val locationRepository: LocationRepository
) {

    operator fun invoke(): Flow<Unit> {
        return cityRepository.getRemote()
            .onEach { cityRepository.insertAll(cityList = it).collect() }
            .onEach { syncBeachList(cityList = it) }
            .map { }
    }

    private suspend fun syncLocationList(beachList: List<Beach>) {
        coroutineScope {
            beachList.forEach { beach ->
                awaitAll(
                    async {
                        locationRepository.getRemoteByBeachId(beachId = beach.id)
                            .onEach { locationRepository.insertAll(locationList = it).collect() }
                            .launchIn(this)
                    }
                )
            }
        }
    }

    private suspend fun syncBeachList(cityList: List<City>) {
        coroutineScope {
            cityList.forEach { city ->
                awaitAll(
                    async {
                        beachRepository.getRemoteByCityId(cityId = city.id)
                            .onEach { beachRepository.insertAll(beachList = it).collect() }
                            .onEach { syncLocationList(beachList = it) }
                            .launchIn(this)
                    }
                )
            }
        }
    }

}