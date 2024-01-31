package dev.aplika.core.database.di

import android.content.Context
import androidx.room.Room
import dev.aplika.core.database.dao.SantaCatarinaCollectPointDao
import dev.aplika.core.database.dao.SantaCatarinaCollectPointWithCollectsDao
import dev.aplika.core.database.dao.SantaCatarinaCollectDao
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
    fun providesSantaCatarinaCollectPointWithCollectsDao(
        database: BathabilityDatabase
    ): SantaCatarinaCollectPointWithCollectsDao {
        return database.santaCatarinaCollectPointWithCollectsDao()
    }

    @Singleton
    @Provides
    fun providesSantaCatarinaCollectPointDao(
        database: BathabilityDatabase
    ): SantaCatarinaCollectPointDao {
        return database.santaCatarinaCollectPointDao()
    }

    @Singleton
    @Provides
    fun providesSantaCatarinaCollectDao(
        database: BathabilityDatabase
    ): SantaCatarinaCollectDao {
        return database.santaCatarinaCollectDao()
    }

    companion object {
        private const val DATABASE_NAME = "database"
    }

}