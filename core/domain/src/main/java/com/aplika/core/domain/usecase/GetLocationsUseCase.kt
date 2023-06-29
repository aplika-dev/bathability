package com.aplika.core.domain.usecase

import com.aplika.core.domain.repository.CityRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLocationsUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {

}