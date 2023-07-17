package dev.aplika.core.network.service

import dev.aplika.core.network.model.BrScMonitoringPointDto
import retrofit2.http.GET

interface BrScService {

    @GET("relatorio/mapa")
    suspend fun getBeachCollects(): List<BrScMonitoringPointDto>

}