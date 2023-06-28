package com.aplika.core.network.service

import com.aplika.core.network.model.BeachDto
import com.aplika.core.network.model.CityDto
import com.aplika.core.network.model.GetBeachesBodyDto
import com.aplika.core.network.model.GetLocationsBodyDto
import com.aplika.core.network.model.LocationDto
import retrofit2.http.Body
import retrofit2.http.POST

interface BathabilityService {

    @POST("municipio/getMunicipios")
    suspend fun getCities(): List<CityDto>

    @POST("local/getLocaisByMunicipio")
    suspend fun getBeaches(
        @Body body: GetBeachesBodyDto
    ): List<BeachDto>

    @POST("local/getLocaisByMunicipio")
    suspend fun getLocations(
        @Body body: GetLocationsBodyDto
    ): List<LocationDto>

}