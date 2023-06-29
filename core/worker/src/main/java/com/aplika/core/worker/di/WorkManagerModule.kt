package com.aplika.core.worker.di

import android.content.Context
import androidx.startup.Initializer
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.aplika.core.worker.worker.GetRemoteLocationListWorker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class WorkManagerModule : Initializer<WorkManager> {

    @Provides
    @Singleton
    override fun create(@ApplicationContext context: Context): WorkManager {
        WorkManager.initialize(context, Configuration.Builder().build())
        return WorkManager.getInstance(context)
            .apply {
                enqueue(
                    OneTimeWorkRequestBuilder<GetRemoteLocationListWorker>()
                        .setConstraints(Constraints(requiredNetworkType = NetworkType.CONNECTED))
                        .build()
                )
            }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }

}