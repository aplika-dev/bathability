package com.aplika.core.domain.repository

import com.aplika.core.domain.model.Beach
import kotlinx.coroutines.flow.Flow

interface BeachRepository {

    fun getByCityId(cityId: String): Flow<List<Beach>>
    fun getRemoteByCityId(cityId: String): Flow<List<Beach>>
    fun insertAll(beachList: List<Beach>): Flow<Unit>

}