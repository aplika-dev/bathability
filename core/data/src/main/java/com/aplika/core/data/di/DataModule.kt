package com.aplika.core.data.di

import com.aplika.core.data.repository.BeachCollectRepositoryImpl
import com.aplika.core.domain.repository.BeachCollectRepository
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
    abstract fun beachCollectRepository(impl: BeachCollectRepositoryImpl): BeachCollectRepository

}