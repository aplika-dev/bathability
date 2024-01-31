package dev.aplika.data.collect_point.santa_catarina.di

import dev.aplika.data.collect_point.santa_catarina.service.SantaCatarinaCollectPointService
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
    ): SantaCatarinaCollectPointService =
        retrofit.create(SantaCatarinaCollectPointService::class.java)

}