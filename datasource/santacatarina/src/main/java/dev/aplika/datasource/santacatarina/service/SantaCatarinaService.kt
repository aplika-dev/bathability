package dev.aplika.datasource.santacatarina.service

import dev.aplika.datasource.santacatarina.model.MonitoringPointDto
import retrofit2.http.GET

interface SantaCatarinaService {

    @GET("relatorio/mapa")
    suspend fun getMonitoringPoints(): List<MonitoringPointDto>

}