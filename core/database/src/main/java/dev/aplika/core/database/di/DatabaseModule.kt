package dev.aplika.core.database.di

import android.content.Context
import androidx.room.Room
import dev.aplika.core.database.dao.BeachDao
import dev.aplika.core.database.dao.BeachWithCollectsDao
import dev.aplika.core.database.dao.CollectDao
import dev.aplika.core.database.database.BathabilityDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(
        @ApplicationContext context: Context
    ): BathabilityDatabase {
        return Room.databaseBuilder(
            context,
            BathabilityDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun providesBeachCollectDao(
        database: BathabilityDatabase
    ): BeachWithCollectsDao {
        return database.beachCollectDao()
    }

    @Singleton
    @Provides
    fun providesBeachDao(
        database: BathabilityDatabase
    ): BeachDao {
        return database.beachDao()
    }

    @Singleton
    @Provides
    fun providesCollectDao(
        database: BathabilityDatabase
    ): CollectDao {
        return database.collectDao()
    }

    companion object {
        private const val DATABASE_NAME = "database"
    }

}