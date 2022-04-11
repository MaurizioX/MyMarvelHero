package mzx.mymarvel.characterlist

import arrow.core.left
import arrow.core.right
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import mzx.mymarvel.characterlist.CharacterListViewModel.CharacterListState.Companion.createInitState
import mzx.mymarvel.data.entity.CharacterEntity
import mzx.mymarvel.data.entity.DataError
import mzx.mymarvel.domain.usecase.GetCharacterListUseCase
import mzx.mymarvel.mapper.CharacterElementMapper
import org.junit.Assert.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

@ExperimentalCoroutinesApi
object CharacterListViewModelTest : Spek({
    val dispatcher: CoroutineDispatcher by memoized { UnconfinedTestDispatcher() }
    beforeEachTest { Dispatchers.setMain(dispatcher) }
    afterEachTest { Dispatchers.resetMain() }

    val getCharacterListUseCase: GetCharacterListUseCase by memoized { mockk() }
    val mapper: CharacterElementMapper by memoized { mockk() }
    describe("A View Model") {
        val viewModel by memoized {
            CharacterListViewModel(
                getCharacterListUseCase = getCharacterListUseCase,
                mapper = mapper
            )
        }
        describe("Initialize View Model") {
            it("it is initialized") {
                assertEquals(
                    createInitState(),
                    viewModel.state
                )
            }

            describe("View model is loaded successfully") {
                val resultList by memoized { emptyList<CharacterEntity>() }
                beforeEachTest { coEvery { getCharacterListUseCase.action() } returns resultList.right() }
                beforeEachTest {
                    viewModel.onEvent(CharacterListViewModel.CharacterListEvent.Init)
                }

                describe("Elements returned") {
                    it("Element Returned") {
                        assertEquals(createInitState().elementLoaded(emptyList()), viewModel.state)
                    }
                }
            }

            describe("View model is loaded with error") {
                val resultError:DataError by memoized { mockk() }
                beforeEachTest { coEvery { getCharacterListUseCase.action() } returns resultError.left() }
                beforeEachTest {
                    viewModel.onEvent(CharacterListViewModel.CharacterListEvent.Init)
                }

                describe("Elements returned") {
                    it("Element Returned") {
                        assertEquals(createInitState().displayError(), viewModel.state)
                    }
                }
            }
        }
    }
})