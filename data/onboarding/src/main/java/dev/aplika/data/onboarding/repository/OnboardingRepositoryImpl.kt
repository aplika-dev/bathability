package dev.aplika.data.onboarding.repository

import dev.aplika.core.domain.repository.OnboardingRepository
import dev.aplika.data.onboarding.datasource.OnboardingLocalDataSource
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnboardingRepositoryImpl @Inject constructor(
    private val localDataSource: OnboardingLocalDataSource
) : OnboardingRepository {

    override suspend fun getAndUpdateIsFirstAccess(): Boolean {
        val isFirstAccess = localDataSource.getIsFirstAccess().firstOrNull() != false
        if (isFirstAccess) localDataSource.setIsFirstAccess(value = false)
        return isFirstAccess
    }

}