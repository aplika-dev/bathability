package dev.aplika.core.network.di

import dev.aplika.core.network.service.SantaCatarinaService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aplika.core.network.di.annotation.BahiaRetrofit
import dev.aplika.core.network.di.annotation.SantaCatarinaRetrofit
import dev.aplika.core.network.service.BahiaService
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal class ServiceModule {

    @Singleton
    @Provides
    fun providesBrScService(
        @SantaCatarinaRetrofit retrofit: Retrofit
    ): SantaCatarinaService =
        retrofit.create(SantaCatarinaService::class.java)

    @Singleton
    @Provides
    fun providesBrBaService(
        @BahiaRetrofit retrofit: Retrofit
    ): BahiaService =
        retrofit.create(BahiaService::class.java)

}