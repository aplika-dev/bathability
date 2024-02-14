package dev.aplika.core.domain.repository

interface OnboardingRepository {

    suspend fun getAndUpdateIsFirstAccess(): Boolean

}