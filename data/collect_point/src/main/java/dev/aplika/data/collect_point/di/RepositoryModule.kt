package dev.aplika.data.collect_point.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aplika.core.domain.repository.CollectPointRepository
import dev.aplika.data.collect_point.repository.CollectPointRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun providesRepository(impl: CollectPointRepositoryImpl): CollectPointRepository

}