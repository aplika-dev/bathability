package dev.aplika.core.network.service

import dev.aplika.core.network.model.BahiaMonitoringPointDto
import retrofit2.http.GET

interface BahiaService {

    @GET("index.php/relatoriodebalneabilidade/listaMarcadores")
    suspend fun getBeachCollects(): List<BahiaMonitoringPointDto>

}