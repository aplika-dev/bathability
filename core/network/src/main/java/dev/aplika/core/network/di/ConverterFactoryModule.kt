package dev.aplika.core.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.aplika.core.network.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter

@Module
@InstallIn(SingletonComponent::class)
internal class ConverterFactoryModule {

    @Singleton
    @Provides
    fun providesConverterFactory(
        json: Json
    ): Converter.Factory =
        json.asConverterFactory(APPLICATION_JSON.toMediaType())

    private companion object {
        private const val APPLICATION_JSON = "application/json"
    }

}