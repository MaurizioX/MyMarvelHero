package mzx.mymarvel.mapper

import io.mockk.every
import io.mockk.mockk
import mzx.mymarvel.data.entity.CharacterEntity
import mzx.mymarvel.ui.model.MarvelCharacterUi
import org.junit.Assert.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


class CharacterElementMapperTest : Spek({
    describe("a mapper") {
        val dateMapper: DateMapper by memoized { mockk() }
        val mapper by memoized { CharacterElementMapper(dateMapper) }


        describe("Map an Entity") {
            val element: CharacterEntity by memoized { mockk(relaxed = true) }
            lateinit var result: MarvelCharacterUi
            beforeEachTest {
                every { element.name } returns "name"
                every { element.id } returns 1
                every { dateMapper.mediumFormat(any()) } returns "some date"
                every { element.description } returns "Some description"
                every { element.thumbnail.path } returns "uriPath"
                every { element.thumbnail.extension } returns "ext"
                result = mapper.map(characterElement = element)
            }
            it("Check response") {
                assertEquals(
                    MarvelCharacterUi(
                        name = "name",
                        url = "uriPath.ext",
                        modifiedDate = "some date",
                        description = "Some description",
                        characterId = 1,
                        comics = emptyList(),
                        series = emptyList(),
                        stories = emptyList()
                    ), result
                )
            }
        }
    }
})