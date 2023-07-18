package dev.aplika.bathability.network.santacatarina.service

import dev.aplika.bathability.network.santacatarina.model.MonitoringPointDto
import retrofit2.http.GET

interface SantaCatarinaService {

    @GET("relatorio/mapa")
    suspend fun getBeachCollects(): List<MonitoringPointDto>

}