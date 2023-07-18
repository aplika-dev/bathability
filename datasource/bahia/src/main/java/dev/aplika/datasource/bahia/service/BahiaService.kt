package dev.aplika.datasource.bahia.service

import dev.aplika.datasource.bahia.model.GetDetailsResponseDto
import dev.aplika.datasource.bahia.model.GetTokenResponseDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface BahiaService {

    @GET("v108/detalhamento-praia")
    suspend fun getDetails(
        @Header("authorization") authorization: String
    ): GetDetailsResponseDto

    @POST("oauth")
    @Multipart
    suspend fun getToken(
        @Header("authorization") authorization: String,
        @Part("grant_type") grantType: String,
        @Part("username") username: String,
        @Part("password") password: String
    ): GetTokenResponseDto

}