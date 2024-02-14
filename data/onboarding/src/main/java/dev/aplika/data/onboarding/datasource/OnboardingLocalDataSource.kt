package dev.aplika.data.onboarding.datasource

import dev.aplika.core.datastore.BathabilityDatastore
import dev.aplika.core.datastore.BathabilityDatastoreKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnboardingLocalDataSource @Inject constructor(
    private val bathabilityDatastore: BathabilityDatastore
) {

    fun getIsFirstAccess(): Flow<Boolean?> {
        return bathabilityDatastore.get(key = BathabilityDatastoreKey.IsFirstAccess)
    }

    suspend fun setIsFirstAccess(value: Boolean) {
        bathabilityDatastore.put(key = BathabilityDatastoreKey.IsFirstAccess, value = value)
    }

}