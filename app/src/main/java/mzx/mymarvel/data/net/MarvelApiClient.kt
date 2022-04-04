package mzx.mymarvel.data.net

import mzx.mymarvel.data.net.model.MarvelListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelApiClient {
    @GET("/v1/public/characters")
    suspend fun getMarvelCharacters(): Response<MarvelListResponse>

    @GET("/v1/public/characters/{id}")
    suspend fun getMarvelCharacterDetail(@Path("id") characterId: String): Response<String>
}