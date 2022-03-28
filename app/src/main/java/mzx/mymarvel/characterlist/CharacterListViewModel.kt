package mzx.mymarvel.characterlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mzx.mymarvel.characterlist.CharacterListViewModel.CharacterListState.Companion.createInitState
import mzx.mymarvel.domain.element.CharacterElement
import mzx.mymarvel.domain.usecase.DomainError
import mzx.mymarvel.domain.usecase.GetCharacterListUseCase
import mzx.mymarvel.mapper.CharacterElementMapper
import mzx.mymarvel.ui.model.MarvelCharacterUi
import mzx.mymarvel.ui.model.State
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase,
    private val mapper: CharacterElementMapper
) : ViewModel() {

    data class CharacterListState(
        val characters: List<MarvelCharacterUi> = emptyList(),
        val isLoading: State = State.INIT
    ) {
        fun isLoading(): CharacterListState = copy(isLoading = State.LOADING)
        fun elementLoaded(characters: List<MarvelCharacterUi>): CharacterListState =
            copy(characters = characters, isLoading = State.LOADED)

        fun displayError(): CharacterListState = copy()

        companion object {
            fun createInitState() = CharacterListState()
        }
    }

    sealed interface CharacterListEvent {
        object Init : CharacterListEvent
    }

    private var _state by mutableStateOf(createInitState())

    fun onEvent(characterListEvent: CharacterListEvent) {
        viewModelScope.launch {
            val currentState = when (characterListEvent) {
                CharacterListEvent.Init -> {
                    _state = state.isLoading()
                    getCharacterListUseCase.action().fold(::handleError, ::handleSuccess)
                }
            }
            _state = currentState
        }
    }

    private fun handleError(domainError: DomainError) = state.displayError()


    private fun handleSuccess(characters: List<CharacterElement>): CharacterListState =
        state.elementLoaded(characters = characters.map(mapper::map))

    val state: CharacterListState
        get() = _state
}