package mzx.mymarvel.data.service.impl

import arrow.core.Either
import mzx.mymarvel.data.entity.CharacterEntity
import mzx.mymarvel.data.entity.DataError
import mzx.mymarvel.data.net.MarvelApiClient
import mzx.mymarvel.data.net.model.MarvelListResponse
import mzx.mymarvel.data.service.CharacterService
import retrofit2.Response
import javax.inject.Inject

class CharacterServiceImpl @Inject constructor(private val marvelApiClient: MarvelApiClient) :
    CharacterService {
    override suspend fun getAllCharacters(): Either<DataError, List<CharacterEntity>> =
        marvelApiClient.getMarvelCharacters()
            ?.let { response: Response<MarvelListResponse> ->
                if (response.isSuccessful) {
                    response.body()?.let { it.data.results }
                } else {
                }
            }


}