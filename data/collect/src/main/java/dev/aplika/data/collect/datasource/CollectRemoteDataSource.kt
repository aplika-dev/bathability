package dev.aplika.data.collect.datasource

import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.android.di.IoDispatcher
import dev.aplika.core.domain.model.Collect
import dev.aplika.core.domain.model.LocalityGroup
import dev.aplika.core.domain.repository.CollectPointRepository
import dev.aplika.data.collect.mapper.RioGrandeDoSulCollectDtoToCollectMapper
import dev.aplika.network.rio_grande_do_sul.model.CollectDto
import dev.aplika.network.rio_grande_do_sul.service.RioGrandeDoSulService
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

@Singleton
class CollectRemoteDataSource @Inject constructor(
    private val rioGrandeDoSulService: RioGrandeDoSulService,
    private val rioGrandeDoSulCollectDtoToCollectMapper: RioGrandeDoSulCollectDtoToCollectMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    suspend fun getByIdAndLocalityGroup(id: String, localityGroup: LocalityGroup): List<Collect> {
        return when (localityGroup) {
            LocalityGroup.RIO_GRANDE_DO_SUL -> getRioGrandeDoSulById(id = id)
            LocalityGroup.SANTA_CATARINA -> throw IllegalStateException("Santa Catarina data should be available at this point")
        }
    }

    private suspend fun getRioGrandeDoSulById(id: String): List<Collect> {
        val collectDtoList = getRioGrandeDoSulByIdRequest(id = id)
        val collectList = mapRioGrandeDoSulCollectDtoListToCollectList(items = collectDtoList)

        return collectList
    }

    private suspend fun getRioGrandeDoSulByIdRequest(id: String): List<CollectDto> {
        return withContext(ioDispatcher) {
            rioGrandeDoSulService.getCollectsById(id = id.toLong())
        }
    }

    private suspend fun mapRioGrandeDoSulCollectDtoListToCollectList(items: List<CollectDto>): List<Collect> {
        return withContext(defaultDispatcher) {
            items.mapNotNull { rioGrandeDoSulCollectDtoToCollectMapper.map(input = it) }
        }
    }

}