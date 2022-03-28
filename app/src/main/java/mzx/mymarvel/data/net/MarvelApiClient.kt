package mzx.mymarvel.data.net

import mzx.mymarvel.data.net.model.MarvelListResponse
import retrofit2.Response
import retrofit2.http.GET

interface MarvelApiClient {
    @GET("/v1/public/characters")
    suspend fun getMarvelCharacters(): Response<MarvelListResponse>
}