package dev.aplika.network.santa_catarina.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class SantaCatarinaRetrofit

@Module
@InstallIn(SingletonComponent::class)
internal class RetrofitModule {

    @Singleton
    @Provides
    @SantaCatarinaRetrofit
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()

    private companion object {
        private const val BASE_URL = "https://balneabilidade.ima.sc.gov.br/"
    }

}