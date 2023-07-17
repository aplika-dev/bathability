package dev.aplika.core.network.service

import dev.aplika.core.network.model.BrBaMonitoringPointCollectDto
import retrofit2.http.GET

interface BrBaService {

    @GET("index.php/relatoriodebalneabilidade/listaMarcadores")
    suspend fun getBeachCollects(): List<BrBaMonitoringPointCollectDto>

}