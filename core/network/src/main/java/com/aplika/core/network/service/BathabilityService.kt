package com.aplika.core.network.service

import com.aplika.core.network.model.BeachDto
import com.aplika.core.network.model.CityDto
import com.aplika.core.network.model.LocationDto
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface BathabilityService {

    @GET("municipio/getMunicipios")
    suspend fun getCities(): List<CityDto>

    @Multipart
    @POST("local/getLocaisByMunicipio")
    suspend fun getBeaches(
        @Part("municipioID") cityId: Int
    ): List<BeachDto>

    @Multipart
    @POST("pontoColeta/getPontosByLocal")
    suspend fun getLocations(
        @Part("localID") beachId: Int
    ): List<LocationDto>

}