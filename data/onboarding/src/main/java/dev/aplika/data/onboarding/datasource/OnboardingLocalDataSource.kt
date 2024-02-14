package dev.aplika.data.onboarding.datasource

import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.datastore.BathabilityDatastore
import dev.aplika.core.datastore.BathabilityDatastoreKey
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnboardingLocalDataSource @Inject constructor(
    private val bathabilityDatastore: BathabilityDatastore,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    fun getIsFirstAccess(): Flow<Boolean> {
        return bathabilityDatastore.get(key = BathabilityDatastoreKey.IsFirstAccess)
            .map { it != false }
            .flowOn(defaultDispatcher)
    }

    suspend fun setIsFirstAccess(value: Boolean) {
        bathabilityDatastore.put(key = BathabilityDatastoreKey.IsFirstAccess, value = value)
    }

}