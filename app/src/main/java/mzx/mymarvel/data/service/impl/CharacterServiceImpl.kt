package mzx.mymarvel.data.service.impl

import android.util.Log
import arrow.core.Either
import mzx.mymarvel.data.entity.CharacterEntity
import mzx.mymarvel.data.entity.DataError
import mzx.mymarvel.data.net.MarvelApiClient
import mzx.mymarvel.data.service.CharacterService
import javax.inject.Inject

class CharacterServiceImpl @Inject constructor(private val marvelApiClient: MarvelApiClient) :
    CharacterService {
    override suspend fun getAllCharacters(): Either<DataError, List<CharacterEntity>> {
        val string = marvelApiClient.getMarvelCharacters()
        Log.i("ASD", string)
        return TODO()
    }
}