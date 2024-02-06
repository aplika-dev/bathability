package dev.aplika.network.rio_grande_do_sul.di

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
annotation class RioGrandeDoSulRetrofit

@Module
@InstallIn(SingletonComponent::class)
internal class RetrofitModule {

    @Singleton
    @Provides
    @RioGrandeDoSulRetrofit
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
        private const val BASE_URL = "https://secweb.procergs.com.br//"
    }

}