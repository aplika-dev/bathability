package dev.aplika.data.collect_point.rio_grande_do_sul.service

import dev.aplika.data.collect_point.rio_grande_do_sul.model.RioGrandeDoSulCollectDto
import dev.aplika.data.collect_point.rio_grande_do_sul.model.RioGrandeDoSulCollectPointDto
import retrofit2.http.GET
import retrofit2.http.Path

interface RioGrandeDoSulService {

    @GET("baln/rest/locais")
    suspend fun getCollectPoints(): List<RioGrandeDoSulCollectPointDto>

    @GET("baln/rest/coletas/{id}")
    suspend fun getCollectsById(
        @Path("id") id: Long
    ): List<RioGrandeDoSulCollectDto>

}