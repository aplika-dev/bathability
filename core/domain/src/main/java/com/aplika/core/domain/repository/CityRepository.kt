package com.aplika.core.domain.repository

import com.aplika.core.domain.model.City
import kotlinx.coroutines.flow.Flow

interface CityRepository {

    fun get(): Flow<List<City>>

}