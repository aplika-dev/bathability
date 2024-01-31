package dev.aplika.core.network.di

import dev.aplika.network.santa_catarina.service.SantaCatarinaService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal class ServiceModule {

    @Singleton
    @Provides
    fun providesService(
        retrofit: Retrofit
    ): SantaCatarinaService =
        retrofit.create(SantaCatarinaService::class.java)

}