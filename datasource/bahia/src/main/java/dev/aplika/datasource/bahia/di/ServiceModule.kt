package dev.aplika.datasource.bahia.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aplika.datasource.bahia.annotation.BahiaRetrofit
import dev.aplika.datasource.bahia.service.BahiaService
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