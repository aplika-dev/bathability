package com.aplika.core.android.di

import com.aplika.core.android.handler.ThrowableHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class ThrowableHandlerModule {

    @Singleton
    @Provides
    fun providesThrowableHandler(): ThrowableHandler {
        return ThrowableHandler()
    }

}