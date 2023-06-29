package com.aplika.core.domain.repository

import com.aplika.core.domain.model.Beach
import com.aplika.core.domain.model.City
import kotlinx.coroutines.flow.Flow

interface BeachRepository {

    fun getByCityId(cityId: String): Flow<List<Beach>>

}