package dev.aplika.core.data.di

import dev.aplika.core.data.repository.MonitoringPointRepositoryImpl
import dev.aplika.core.domain.repository.MonitoringPointRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun monitoringPointRepository(impl: MonitoringPointRepositoryImpl): MonitoringPointRepository

}