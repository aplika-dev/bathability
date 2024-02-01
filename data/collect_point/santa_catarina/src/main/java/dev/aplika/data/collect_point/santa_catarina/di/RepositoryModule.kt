package dev.aplika.data.collect_point.santa_catarina.di

import dev.aplika.data.collect_point.santa_catarina.repository.SantaCatarinaCollectPointRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun providesRepository(impl: SantaCatarinaCollectPointRepositoryImpl): SantaCatarinaCollectPointRepository

}