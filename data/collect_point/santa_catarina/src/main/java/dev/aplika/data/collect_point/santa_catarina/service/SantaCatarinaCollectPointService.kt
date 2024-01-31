package dev.aplika.data.collect_point.santa_catarina.service

import dev.aplika.data.collect_point.santa_catarina.model.SantaCatarinaCollectPointDto
import retrofit2.http.GET

interface SantaCatarinaCollectPointService {

    @GET("relatorio/mapa")
    suspend fun getBeachCollects(): List<SantaCatarinaCollectPointDto>

}