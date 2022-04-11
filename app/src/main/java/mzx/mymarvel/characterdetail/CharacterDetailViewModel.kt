package mzx.mymarvel.characterdetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mzx.mymarvel.data.entity.CharacterEntity
import mzx.mymarvel.data.entity.DataError
import mzx.mymarvel.domain.usecase.GetCharacterDetailUseCase
import mzx.mymarvel.mapper.CharacterElementMapper
import mzx.mymarvel.ui.model.MarvelCharacterUi
import mzx.mymarvel.ui.model.Status
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
    private val mapper: CharacterElementMapper
) : ViewModel() {
    data class CharacterDetailState(
        val character: MarvelCharacterUi? = null,
        val status: Status = Status.INIT
    ) {
        fun isLoading(): CharacterDetailState = copy(status = Status.LOADING)
        fun elementLoaded(characters: MarvelCharacterUi): CharacterDetailState =
            copy(character = characters, status = Status.LOADED)

        fun displayError(): CharacterDetailState = copy()

        companion object {
            fun createInitState() = CharacterDetailState()
        }
    }

    sealed interface CharacterDetailEvent {
        data class Init(val characterID: Int) : CharacterDetailEvent
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
            _state = currentState
        }
    }

    private fun handleError(domainError: DataError) = state.displayError()

    private fun handleSuccess(character: CharacterEntity): CharacterDetailState =
        state.elementLoaded(character.let(mapper::map))

    val state: CharacterDetailState
        get() = _state

    private var _state by mutableStateOf(CharacterDetailState.createInitState())
}