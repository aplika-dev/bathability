package dev.aplika.core.network.di

import dev.aplika.core.network.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
internal class OkHttpClientModule {

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .apply { if (BuildConfig.DEBUG) addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)) }
            .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .build()

    private companion object {
        private const val TIMEOUT_IN_SECONDS = 60L
    }

}