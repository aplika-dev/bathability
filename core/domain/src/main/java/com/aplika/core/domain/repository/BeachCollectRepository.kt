package com.aplika.core.domain.repository

import com.aplika.core.domain.model.BeachCollect
import kotlinx.coroutines.flow.Flow

interface BeachCollectRepository {

    fun getAll(): Flow<List<BeachCollect>>
    fun sync(): Flow<Unit>

}