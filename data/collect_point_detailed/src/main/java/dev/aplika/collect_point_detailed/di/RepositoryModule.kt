package dev.aplika.collect_point_detailed.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aplika.collect_point_detailed.repository.CollectPointDetailedRepositoryImpl
import dev.aplika.core.domain.repository.CollectPointDetailedRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun providesRepository(impl: CollectPointDetailedRepositoryImpl): CollectPointDetailedRepository

}