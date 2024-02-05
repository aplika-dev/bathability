package dev.aplika.data.collect_point.datasource

import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.android.di.IoDispatcher
import dev.aplika.core.android.extensions.suspendRunCatching
import dev.aplika.core.domain.model.CollectPoint
import dev.aplika.core.domain.model.CollectPointWithCollects
import dev.aplika.core.domain.repository.CollectRepository
import dev.aplika.data.collect_point.mapper.RioGrandeDoSulCollectPointDtoToCollectPointMapper
import dev.aplika.data.collect_point.mapper.SantaCatarinaCollectPointDtoToCollectPointMapper
import dev.aplika.data.collect_point.mapper.SantaCatarinaCollectPointDtoToCollectPointWithCollectsMapper
import dev.aplika.network.rio_grande_do_sul.service.RioGrandeDoSulService
import dev.aplika.network.rio_grande_do_sul.model.CollectPointDto as RioGrandeDoSulCollectPointDto
import dev.aplika.network.santa_catarina.model.CollectPointDto as SantaCatarinaCollectPointDto
import dev.aplika.network.santa_catarina.service.SantaCatarinaService
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

@Singleton
class CollectPointRemoteDataSource @Inject constructor(
    private val santaCatarinaService: SantaCatarinaService,
    private val rioGrandeDoSulService: RioGrandeDoSulService,
    private val santaCatarinaCollectPointDtoToCollectPointMapper: SantaCatarinaCollectPointDtoToCollectPointMapper,
    private val rioGrandeDoSulCollectPointDtoToCollectPointMapper: RioGrandeDoSulCollectPointDtoToCollectPointMapper,
    private val santaCatarinaCollectPointDtoToCollectPointWithCollectsMapper: SantaCatarinaCollectPointDtoToCollectPointWithCollectsMapper,
    private val collectRepository: CollectRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    suspend fun fetchAllCatching(): List<CollectPoint> {
        return getAllFromRioGrandeDoSul() + getAllFromSantaCatarina()
    }

    private suspend fun getAllFromRioGrandeDoSul(): List<CollectPoint> {
        val collectPointDtoList = suspendRunCatching { getAllFromRioGrandeDoSulRequest() }.orEmpty()
        val collectPointList = mapRioGrandeDoSulCollectPointDtoToCollectPoint(items = collectPointDtoList)

        return collectPointList
    }

    private suspend fun getAllFromRioGrandeDoSulRequest(): List<RioGrandeDoSulCollectPointDto> {
        return withContext(ioDispatcher) {
            rioGrandeDoSulService.getCollectPoints()
        }
    }

    private suspend fun mapRioGrandeDoSulCollectPointDtoToCollectPoint(items: List<RioGrandeDoSulCollectPointDto>): List<CollectPoint> {
        return withContext(defaultDispatcher) {
            items.map { rioGrandeDoSulCollectPointDtoToCollectPointMapper.map(input = it) }
        }
    }

    private suspend fun getAllFromSantaCatarina(): List<CollectPoint> {
        val collectPointDtoList = suspendRunCatching { getAllFromSantaCatarinaRequest() }.orEmpty()
        insertAllSantaCatarinaCollects(collectPoints = collectPointDtoList)
        val collectPointList = mapSantaCatarinaCollectPointDtoToCollectPoint(items = collectPointDtoList)

        return collectPointList
    }

    private suspend fun getAllFromSantaCatarinaRequest(): List<SantaCatarinaCollectPointDto> {
        return withContext(ioDispatcher) {
            santaCatarinaService.getCollectPoints()
        }
    }

    private suspend fun mapSantaCatarinaCollectPointDtoToCollectPoint(items: List<SantaCatarinaCollectPointDto>): List<CollectPoint> {
        return withContext(defaultDispatcher) {
            items.map { santaCatarinaCollectPointDtoToCollectPointMapper.map(input = it) }
        }
    }

    private suspend fun insertAllSantaCatarinaCollects(collectPoints: List<SantaCatarinaCollectPointDto>) {
        val collectPointWithCollectsList = mapSantaCatarinaCollectPointDtoToCollectPointWithCollects(items = collectPoints)
        collectRepository.insertAll(items = collectPointWithCollectsList)
    }

    private suspend fun mapSantaCatarinaCollectPointDtoToCollectPointWithCollects(items: List<SantaCatarinaCollectPointDto>): List<CollectPointWithCollects> {
        return withContext(defaultDispatcher) {
            items.map { santaCatarinaCollectPointDtoToCollectPointWithCollectsMapper.map(input = it) }
        }
    }

}