package dev.aplika.core.network.di

import dev.aplika.core.network.service.BathabilityService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aplika.core.network.di.annotation.BrScRetrofit
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal class ServiceModule {

    @Singleton
    @Provides
    fun providesService(
        @BrScRetrofit retrofit: Retrofit
    ): BathabilityService =
        retrofit.create(BathabilityService::class.java)

}