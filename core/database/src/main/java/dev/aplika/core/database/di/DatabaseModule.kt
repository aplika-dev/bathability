package dev.aplika.core.database.di

import android.content.Context
import androidx.room.Room
import dev.aplika.core.database.dao.MonitoringPointDao
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
    fun providesMonitoringPointDao(
        database: BathabilityDatabase
    ): MonitoringPointDao {
        return database.monitoringPointDao()
    }

    companion object {
        private const val DATABASE_NAME = "database"
    }

}