package dev.aplika.core.domain.usecase

import dev.aplika.core.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShouldShowInAppReviewUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {

    suspend operator fun invoke(): Boolean {
        return !onboardingRepository.getAndUpdateIsFirstAccess()
    }

}