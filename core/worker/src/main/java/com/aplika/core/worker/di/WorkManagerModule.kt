package com.aplika.core.worker.di

import android.content.Context
import androidx.startup.Initializer
import androidx.work.Configuration
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
        WorkManager.getInstance(context)
            .also { it.runWorkers() }
            .also { return it }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }

    private fun WorkManager.runWorkers() {
        ON_CREATE_WORKERS.forEach { enqueue(it) }
    }

    companion object {
        private val ON_CREATE_WORKERS = listOf(
            OneTimeWorkRequestBuilder<GetRemoteLocationListWorker>().build()
        )
    }

}