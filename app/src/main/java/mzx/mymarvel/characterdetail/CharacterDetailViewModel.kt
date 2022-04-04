package mzx.mymarvel.characterdetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mzx.mymarvel.characterlist.CharacterListViewModel
import mzx.mymarvel.data.entity.DataError
import mzx.mymarvel.domain.usecase.GetCharacterDetailUseCase
import mzx.mymarvel.ui.model.MarvelCharacterUi
import mzx.mymarvel.ui.model.State
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
) : ViewModel() {
    data class CharacterDetailState(
        val characters: List<MarvelCharacterUi> = emptyList(),
        val state: State = State.INIT
    ) {
        fun isLoading(): CharacterDetailState = copy(state = State.LOADING)
        fun elementLoaded(characters: List<MarvelCharacterUi>): CharacterDetailState =
            copy(characters = characters, state = State.LOADED)

        fun displayError(): CharacterDetailState = copy()

        companion object {
            fun createInitState() = CharacterDetailState()
        }
    }

    sealed interface CharacterDetailEvent {
        data class Init(val characterID: String) : CharacterDetailEvent
    }

    fun onEvent(characterDetailEvent: CharacterDetailEvent) {
        viewModelScope.launch {
            val currentState = when (characterDetailEvent) {
                is CharacterDetailEvent.Init -> {
                    _state = state.isLoading()
                    getCharacterDetailUseCase.action(characterDetailEvent.characterID)
                        .fold(::handleError, ::handleSuccess)
                }
            }
//            _state = currentState
        }
    }

    private fun handleError(domainError: DataError) = state.displayError()


    private fun handleSuccess(characters: String): CharacterListViewModel.CharacterListState =
        TODO()

    val state: CharacterDetailState
        get() = _state

    private var _state by mutableStateOf(CharacterDetailState.createInitState())
}