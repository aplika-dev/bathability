package dev.aplika.core.network.di

import dev.aplika.core.network.service.BrScService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aplika.core.network.di.annotation.BrBaRetrofit
import dev.aplika.core.network.di.annotation.BrScRetrofit
import dev.aplika.core.network.service.BrBaService
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal class ServiceModule {

    @Singleton
    @Provides
    fun providesBrScService(
        @BrScRetrofit retrofit: Retrofit
    ): BrScService =
        retrofit.create(BrScService::class.java)

    @Singleton
    @Provides
    fun providesBrBaService(
        @BrBaRetrofit retrofit: Retrofit
    ): BrBaService =
        retrofit.create(BrBaService::class.java)

}