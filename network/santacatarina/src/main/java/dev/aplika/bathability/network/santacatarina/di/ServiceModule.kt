package dev.aplika.bathability.network.santacatarina.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aplika.bathability.network.santacatarina.service.SantaCatarinaService
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal class ServiceModule {

    @Singleton
    @Provides
    fun providesService(
        @SantaCatarinaRetrofit retrofit: Retrofit
    ): SantaCatarinaService =
        retrofit.create(SantaCatarinaService::class.java)

}