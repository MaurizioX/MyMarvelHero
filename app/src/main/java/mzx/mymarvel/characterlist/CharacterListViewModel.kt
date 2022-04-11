package mzx.mymarvel.characterlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mzx.mymarvel.characterlist.CharacterListViewModel.CharacterListState.Companion.createInitState
import mzx.mymarvel.data.entity.CharacterEntity
import mzx.mymarvel.data.entity.DataError
import mzx.mymarvel.domain.usecase.GetCharacterListUseCase
import mzx.mymarvel.mapper.CharacterElementMapper
import mzx.mymarvel.ui.model.MarvelCharacterUi
import mzx.mymarvel.ui.model.Status
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase,
    private val mapper: CharacterElementMapper
) : ViewModel() {

    data class CharacterListState(
        val characters: List<MarvelCharacterUi> = emptyList(),
        val status: Status = Status.INIT
    ) {
        fun isLoading(): CharacterListState = copy(status = Status.LOADING)
        fun elementLoaded(characters: List<MarvelCharacterUi>): CharacterListState =
            copy(characters = characters, status = Status.LOADED)

        fun displayError(): CharacterListState = copy(status = Status.ERROR)

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

    private fun handleError(domainError: DataError) = state.displayError()


    private fun handleSuccess(characters: List<CharacterEntity>): CharacterListState =
        state.elementLoaded(characters = characters.map(mapper::map))

    val state: CharacterListState
        get() = _state
}