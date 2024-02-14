package dev.aplika.core.domain.usecase

import dev.aplika.core.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetIsFirstAccessUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {

    operator fun invoke(): Flow<Boolean> {
        return onboardingRepository.getIsFirstAccess()
    }

}