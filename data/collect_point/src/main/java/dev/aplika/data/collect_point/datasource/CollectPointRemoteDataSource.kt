package dev.aplika.data.collect_point.datasource

import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.android.di.IoDispatcher
import dev.aplika.core.domain.model.CollectPoint
import dev.aplika.core.domain.repository.CollectRepository
import dev.aplika.data.collect_point.mapper.RioGrandeDoSulCollectPointDtoToCollectPointMapper
import dev.aplika.data.collect_point.mapper.SantaCatarinaCollectDtoToCollectMapper
import dev.aplika.data.collect_point.mapper.SantaCatarinaCollectPointDtoToCollectPointMapper
import dev.aplika.data.collect_point.mapper.SantaCatarinaCollectPointDtoToCollectPointWithCollectsMapper
import dev.aplika.network.rio_grande_do_sul.service.RioGrandeDoSulService
import dev.aplika.network.santa_catarina.model.CollectPointDto
import dev.aplika.network.santa_catarina.service.SantaCatarinaService
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

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

    fun getAll(): Flow<List<CollectPoint>> {
        return combine(
            flows = listOf(
                getAllFromSantaCatarina(),
                getAllFromRioGrandeDoSul()
            )
        ) { it.toList().flatten() }
    }

    private fun getAllFromRioGrandeDoSul(): Flow<List<CollectPoint>> {
        return flow { emit(rioGrandeDoSulService.getCollectPoints()) }
            .flowOn(ioDispatcher)
            .map { collectPoints ->
                collectPoints.map {
                    rioGrandeDoSulCollectPointDtoToCollectPointMapper.map(
                        input = it
                    )
                }
            }
            .flowOn(defaultDispatcher)
    }

    private fun getAllFromSantaCatarina(): Flow<List<CollectPoint>> {
        return flow { emit(santaCatarinaService.getCollectPoints()) }
            .flowOn(ioDispatcher)
            .onEach { insertAllSantaCatarinaCollects(collectPoints = it) }
            .map { collectPoints ->
                collectPoints.map {
                    santaCatarinaCollectPointDtoToCollectPointMapper.map(
                        input = it
                    )
                }
            }
            .flowOn(defaultDispatcher)
    }

    private suspend fun insertAllSantaCatarinaCollects(collectPoints: List<CollectPointDto>) {
        return flowOf(collectPoints)
            .map { list -> list.map { santaCatarinaCollectPointDtoToCollectPointWithCollectsMapper.map(input = it) } }
            .flowOn(defaultDispatcher)
            .flatMapConcat { collectRepository.insertAll(items = it) }
            .collect()
    }

}