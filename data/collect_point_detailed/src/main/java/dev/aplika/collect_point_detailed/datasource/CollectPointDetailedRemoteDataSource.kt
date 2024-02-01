package dev.aplika.collect_point_detailed.datasource

import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.android.di.IoDispatcher
import dev.aplika.network.santa_catarina.service.SantaCatarinaService
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CollectPointDetailedRemoteDataSource @Inject constructor(
    private val santaCatarinaService: SantaCatarinaService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {



}