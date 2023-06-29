package com.aplika.core.domain.repository

import com.aplika.core.domain.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    fun getByBeachId(beachId: String): Flow<List<Location>>
    fun getRemoteByBeachId(beachId: String): Flow<List<Location>>
    fun insertAll(locationList: List<Location>): Flow<Unit>

}