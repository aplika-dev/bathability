package dev.aplika.data.collect.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aplika.core.domain.repository.CollectRepository
import dev.aplika.data.collect.repository.CollectRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun providesRepository(impl: CollectRepositoryImpl): CollectRepository

}