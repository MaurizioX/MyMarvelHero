package mzx.mymarvel.domain.usecase.impl

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import mzx.mymarvel.data.entity.CharacterEntity
import mzx.mymarvel.data.entity.DataError
import mzx.mymarvel.data.mapper.DomainMapper
import mzx.mymarvel.data.service.CharacterService
import mzx.mymarvel.domain.element.CharacterElement
import mzx.mymarvel.domain.usecase.DomainError
import org.junit.Assert.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


class GetCharacterListUseCaseImplTest : Spek({
    describe("A Use Case") {
        val characterService: CharacterService by memoized { mockk() }
        val mapper: DomainMapper by memoized { mockk() }
        val getCharacterListUseCaseImpl by memoized {
            GetCharacterListUseCaseImpl(
                characterService,
                mapper
            )
        }
        val eitherAsd: Either<DataError, List<CharacterEntity>> by memoized { mockk() }
        val dataError: DataError by memoized { mockk() }
        val characterElements: List<CharacterEntity> by memoized { mockk() }
        lateinit var results: Either<DomainError, List<CharacterElement>>
        beforeEachTest {
            coEvery {
                characterService.getAllCharacters()
            } returns eitherAsd
            coEvery {
                mapper.map(dataError = any())
            } returns dataError.left()
            coEvery { mapper.map(characterEntities = any()) } returns characterElements.right()


        }
        it("success result value") {

            runBlocking {
                assertEquals(
                    getCharacterListUseCaseImpl.action(),
                    emptyList<CharacterElement>().right()
                )
            }
        }
    }
})