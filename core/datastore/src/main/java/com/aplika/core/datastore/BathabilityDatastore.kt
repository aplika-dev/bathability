package com.aplika.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.aplika.core.android.di.DefaultDispatcher
import com.aplika.core.android.di.IoDispatcher
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

@Singleton
class BathabilityDatastore @Inject constructor(
    @ApplicationContext private val context: Context,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    private val Context.dataStore by preferencesDataStore(name = DATA_STORE_NAME)

    fun <T> get(key: VestibularioDataStoreKey<T>): Flow<T?> =
        context.dataStore.data
            .flowOn(ioDispatcher)
            .map { it[key.key] }
            .flowOn(defaultDispatcher)

    suspend fun <T> put(key: VestibularioDataStoreKey<T>, value: T) {
        withContext(ioDispatcher) {
            context.dataStore.edit { it[key.key] = value }
        }
    }

    suspend fun <T> remove(key: VestibularioDataStoreKey<T>) {
        withContext(ioDispatcher) {
            context.dataStore.edit { it.remove(key.key) }
        }
    }

    suspend fun clear() {
        withContext(ioDispatcher) {
            context.dataStore.edit { it.clear() }
        }
    }

    companion object {
        private const val DATA_STORE_NAME = "datastore"
    }

}