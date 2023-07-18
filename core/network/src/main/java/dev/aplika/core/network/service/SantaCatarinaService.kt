package dev.aplika.core.network.service

import dev.aplika.core.network.model.SantaCatarinaMonitoringPointDto
import retrofit2.http.GET

interface SantaCatarinaService {

    @GET("relatorio/mapa")
    suspend fun getBeachCollects(): List<SantaCatarinaMonitoringPointDto>

}