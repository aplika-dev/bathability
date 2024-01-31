package dev.aplika.data.collect_point.rio_grande_do_sul.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aplika.data.collect_point.rio_grande_do_sul.service.RioGrandeDoSulService
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal class ServiceModule {

    @Singleton
    @Provides
    fun providesService(
        retrofit: Retrofit
    ): RioGrandeDoSulService =
        retrofit.create(RioGrandeDoSulService::class.java)

}