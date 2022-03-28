package mzx.mymarvel.data.net

import retrofit2.http.GET

interface MarvelApiClient {
    @GET("/v1/public/characters")
    suspend fun getMarvelCharacters(): String
}