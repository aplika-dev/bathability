package dev.aplika.data.collect_point.di

import dev.aplika.data.collect_point.repository.CollectPointRepositoryImpl
import dev.aplika.core.domain.repository.CollectPointRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CollectPointModule {

    @Singleton
    @Binds
    abstract fun collectPointRepository(impl: CollectPointRepositoryImpl): CollectPointRepository

}