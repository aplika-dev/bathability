package dev.aplika.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {

    fun getIsFirstAccess(): Flow<Boolean>
    suspend fun setIsFirstAccess(value: Boolean)

}