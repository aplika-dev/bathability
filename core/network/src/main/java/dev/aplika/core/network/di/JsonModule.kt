package dev.aplika.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
internal class JsonModule {

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun providesJson(): Json =
        Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }

}