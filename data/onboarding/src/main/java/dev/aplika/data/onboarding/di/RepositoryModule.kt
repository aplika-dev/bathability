package dev.aplika.data.onboarding.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aplika.core.domain.repository.OnboardingRepository
import dev.aplika.data.onboarding.repository.OnboardingRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun providesRepository(impl: OnboardingRepositoryImpl): OnboardingRepository

}