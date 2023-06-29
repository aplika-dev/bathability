package com.aplika.core.worker.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.aplika.core.android.di.DefaultDispatcher
import com.aplika.core.domain.model.Beach
import com.aplika.core.domain.model.City
import com.aplika.core.domain.repository.BeachRepository
import com.aplika.core.domain.repository.CityRepository
import com.aplika.core.domain.repository.LocationRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

@HiltWorker
internal class GetRemoteLocationListWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val cityRepository: CityRepository,
    private val beachRepository: BeachRepository,
    private val locationRepository: LocationRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        withContext(defaultDispatcher) {
            val cityList = cityRepository.getRemote().first()
            cityRepository.insertAll(cityList = cityList).collect()
            syncBeachList(cityList = cityList)
        }

        // TODO: verify catch

        return Result.success()
    }

    private suspend fun syncLocationList(beachList: List<Beach>) {
        coroutineScope {
            beachList.forEach { beach ->
                awaitAll(
                    async {
                        val locationList =
                            locationRepository.getRemoteByBeachId(beachId = beach.id).first()
                        locationRepository.insertAll(locationList = locationList).collect()
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
                        val beachList = beachRepository.getRemoteByCityId(cityId = city.id).first()
                        beachRepository.insertAll(beachList = beachList).collect()
                        syncLocationList(beachList = beachList)
                    }
                )
            }
        }
    }

}