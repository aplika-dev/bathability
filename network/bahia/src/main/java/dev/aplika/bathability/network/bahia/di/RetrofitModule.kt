package dev.aplika.bathability.network.bahia.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aplika.core.network.di.annotation.BahiaRetrofit
import dev.aplika.core.network.di.annotation.SantaCatarinaRetrofit
import dev.aplika.core.network.extensions.APPLICATION_JSON
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
    @BahiaRetrofit
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BAHIA_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(String.APPLICATION_JSON.toMediaType()))
            .build()

    private companion object {
        private const val BAHIA_BASE_URL = "https://api-balneabilidade-homo.inema.ba.gov.br/"
    }

}