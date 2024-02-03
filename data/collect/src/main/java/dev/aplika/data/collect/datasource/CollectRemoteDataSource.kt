package dev.aplika.data.collect.datasource

import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.android.di.IoDispatcher
import dev.aplika.core.domain.model.Collect
import dev.aplika.core.domain.model.LocalityGroup
import dev.aplika.core.domain.repository.CollectPointRepository
import dev.aplika.data.collect.mapper.RioGrandeDoSulCollectDtoToCollectMapper
import dev.aplika.network.rio_grande_do_sul.service.RioGrandeDoSulService
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

@Singleton
class CollectRemoteDataSource @Inject constructor(
    private val rioGrandeDoSulService: RioGrandeDoSulService,
    private val rioGrandeDoSulCollectDtoToCollectMapper: RioGrandeDoSulCollectDtoToCollectMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    fun getByIdAndLocalityGroup(id: String, localityGroup: LocalityGroup): Flow<List<Collect>> {
        return when (localityGroup) {
            LocalityGroup.RIO_GRANDE_DO_SUL -> getRioGrandeDoSulById(id = id)
            LocalityGroup.SANTA_CATARINA -> throw IllegalStateException("This should never happened")
        }
    }

    private fun getRioGrandeDoSulById(id: String): Flow<List<Collect>> {
        return flow { emit(rioGrandeDoSulService.getCollectsById(id = id.toLong())) }
            .flowOn(ioDispatcher)
            .map { list -> list.mapNotNull { rioGrandeDoSulCollectDtoToCollectMapper.map(input = it) } }
            .flowOn(defaultDispatcher)
    }

}