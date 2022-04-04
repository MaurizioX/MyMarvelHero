package mzx.mymarvel.data.service.impl

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import mzx.mymarvel.data.entity.CharacterEntity
import mzx.mymarvel.data.entity.DataError
import mzx.mymarvel.data.net.MarvelApiClient
import mzx.mymarvel.data.net.model.MarvelListResponse
import mzx.mymarvel.data.service.CharacterService
import mzx.mymarvel.data.service.Mapper
import retrofit2.Response
import javax.inject.Inject

class CharacterServiceImpl @Inject constructor(
    private val marvelApiClient: MarvelApiClient,
    private val mapper: Mapper
) :
    CharacterService {
    override suspend fun getAllCharacters(): Either<DataError, List<CharacterEntity>> =
        marvelApiClient.getMarvelCharacters()
            .let { response: Response<MarvelListResponse> ->
                if (response.isSuccessful) {
                    response.body()?.data?.results?.map(mapper::map)?.right()
                        ?: DataError.NoBodyResponse.left()
                } else {
                    mapper.map(response.code()).left()
                }
            }

    override suspend fun fetchCharacterDetail(characterID: String): Either<DataError, String> =
        marvelApiClient.getMarvelCharacterDetail(characterID)
            .let { response: Response<String> ->
                if (response.isSuccessful) {
                    response.body()?.right()
                        ?: DataError.NoBodyResponse.left()
                } else {
                    mapper.map(response.code()).left()
                }
            }
}