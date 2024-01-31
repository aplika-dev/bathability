package dev.aplika.network.santa_catarina.service

import dev.aplika.network.santa_catarina.model.CollectPointDto
import retrofit2.http.GET

interface SantaCatarinaService {

    @GET("relatorio/mapa")
    suspend fun getBeachCollects(): List<CollectPointDto>

}