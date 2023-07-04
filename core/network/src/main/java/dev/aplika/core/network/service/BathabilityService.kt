package dev.aplika.core.network.service

import dev.aplika.core.network.model.BeachCollectDto
import retrofit2.http.GET

interface BathabilityService {

    @GET("relatorio/mapa")
    suspend fun getBeachCollects(): List<BeachCollectDto>

}