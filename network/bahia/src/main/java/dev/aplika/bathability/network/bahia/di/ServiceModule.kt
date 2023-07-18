package dev.aplika.bathability.network.bahia.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aplika.bathability.network.bahia.service.BahiaService
import dev.aplika.core.network.di.annotation.BahiaRetrofit
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal class ServiceModule {

    @Singleton
    @Provides
    fun providesService(
        @BahiaRetrofit retrofit: Retrofit
    ): BahiaService =
        retrofit.create(BahiaService::class.java)

}