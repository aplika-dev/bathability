package dev.aplika.network.rio_grande_do_sul.service

import dev.aplika.network.rio_grande_do_sul.model.CollectDto
import dev.aplika.network.rio_grande_do_sul.model.CollectPointDto
import retrofit2.http.GET
import retrofit2.http.Path

interface RioGrandeDoSulService {

    @GET("baln/rest/locais")
    suspend fun getCollectPoints(): List<CollectPointDto>

    @GET("baln/rest/coletas/{id}")
    suspend fun getCollectsById(
        @Path("id") id: Long
    ): List<CollectDto>

}