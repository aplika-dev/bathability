package dev.aplika.core.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aplika.core.network.di.annotation.BahiaRetrofit
import dev.aplika.core.network.di.annotation.SantaCatarinaRetrofit
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
internal class RetrofitModule {

    @Singleton
    @Provides
    @SantaCatarinaRetrofit
    fun providesSantaCatarinaRetrofit(
        okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(SANTA_CATARINA_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(APPLICATION_JSON.toMediaType()))
            .build()

    @Singleton
    @Provides
    @BahiaRetrofit
    fun providesBahiaRetrofit(
        okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BAHIA_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(APPLICATION_JSON.toMediaType()))
            .build()

    private companion object {
        private const val APPLICATION_JSON = "application/json"

        private const val SANTA_CATARINA_BASE_URL = "https://balneabilidade.ima.sc.gov.br/"
        private const val BAHIA_BASE_URL = "http://balneabilidade.inema.ba.gov.br/"
    }

}