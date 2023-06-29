package com.aplika.core.data.di

import com.aplika.core.data.repository.BeachRepositoryImpl
import com.aplika.core.data.repository.CityRepositoryImpl
import com.aplika.core.data.repository.LocationRepositoryImpl
import com.aplika.core.domain.repository.BeachRepository
import com.aplika.core.domain.repository.CityRepository
import com.aplika.core.domain.repository.LocationRepository
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
    abstract fun cityRepository(impl: CityRepositoryImpl): CityRepository

    @Singleton
    @Binds
    abstract fun beachRepository(impl: BeachRepositoryImpl): BeachRepository

    @Singleton
    @Binds
    abstract fun locationRepository(impl: LocationRepositoryImpl): LocationRepository

}