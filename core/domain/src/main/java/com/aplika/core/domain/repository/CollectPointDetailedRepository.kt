package com.aplika.core.domain.repository

import com.aplika.core.domain.model.CollectPointDetailed
import kotlinx.coroutines.flow.Flow

interface CollectPointDetailedRepository {

    fun get(): Flow<List<CollectPointDetailed>>

}