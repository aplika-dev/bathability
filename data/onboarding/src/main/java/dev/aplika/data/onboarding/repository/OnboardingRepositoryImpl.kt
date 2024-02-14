package dev.aplika.data.onboarding.repository

import dev.aplika.core.domain.repository.OnboardingRepository
import dev.aplika.data.onboarding.datasource.OnboardingLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnboardingRepositoryImpl @Inject constructor(
    private val localDataSource: OnboardingLocalDataSource
) : OnboardingRepository {

    override fun getIsFirstAccess(): Flow<Boolean> {
        return localDataSource.getIsFirstAccess()
    }

    override suspend fun setIsFirstAccess(value: Boolean) {
        localDataSource.setIsFirstAccess(value = value)
    }
}