package dev.aplika.core.domain.usecase

import dev.aplika.core.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SetIsFirstAccessUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {

    suspend operator fun invoke(value: Boolean) {
        return onboardingRepository.setIsFirstAccess(value = value)
    }

}