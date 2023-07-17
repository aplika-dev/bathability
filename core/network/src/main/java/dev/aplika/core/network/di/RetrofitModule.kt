package dev.aplika.core.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aplika.core.network.di.annotation.BrBaRetrofit
import dev.aplika.core.network.di.annotation.BrScRetrofit
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Qualifier


@Module
@InstallIn(SingletonComponent::class)
internal class RetrofitModule {

    @Singleton
    @Provides
    @BrScRetrofit
    fun providesBrScRetrofit(
        okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BR_SC_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(APPLICATION_JSON.toMediaType()))
            .build()

    @Singleton
    @Provides
    @BrBaRetrofit
    fun providesBrBaRetrofit(
        okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BR_BA_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(APPLICATION_JSON.toMediaType()))
            .build()

    private companion object {
        private const val APPLICATION_JSON = "application/json"

        private const val BR_SC_BASE_URL = "https://balneabilidade.ima.sc.gov.br/"
        private const val BR_BA_BASE_URL = "http://balneabilidade.inema.ba.gov.br/"
    }

}