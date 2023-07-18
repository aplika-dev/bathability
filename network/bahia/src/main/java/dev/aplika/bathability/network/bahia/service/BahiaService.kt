package dev.aplika.bathability.network.bahia.service

import dev.aplika.bathability.network.bahia.model.GetDetailsDto
import dev.aplika.bathability.network.bahia.model.GetTokenDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface BahiaService {

    @GET("v108/detalhamento-praia")
    suspend fun getDetails(
        @Header("authorization") token: String
    ): GetDetailsDto

    @POST("oauth")
    @Multipart
    suspend fun getToken(
        @Header("authorization") token: String,
        @Part("grant_type") grantType: String,
        @Part("username") username: String,
        @Part("password") password: String
    ): GetTokenDto

}