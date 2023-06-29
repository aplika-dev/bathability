package com.aplika.core.database.di

import android.content.Context
import androidx.room.Room
import com.aplika.core.database.dao.CityDao
import com.aplika.core.database.database.BathabilityDatabase
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
        ).build()
    }

    @Singleton
    @Provides
    fun providesCityDao(
        database: BathabilityDatabase
    ): CityDao {
        return database.cityDao()
    }

    companion object {
        private const val DATABASE_NAME = "database"
    }

}