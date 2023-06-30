package com.aplika.core.domain.repository

import com.aplika.core.domain.model.CollectPoint
import kotlinx.coroutines.flow.Flow

interface CollectPointRepository {

    fun getByBeachId(beachId: String): Flow<List<CollectPoint>>
    fun getRemoteByBeachId(beachId: String): Flow<List<CollectPoint>>
    fun insertAll(collectPointList: List<CollectPoint>): Flow<Unit>

}