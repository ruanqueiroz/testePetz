package br.com.testpetz.card.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.network.entity.onError
import br.com.network.entity.onSuccess
import br.com.testpetz.card.components.scene.Async
import br.com.testpetz.card.domain.usecase.CardInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CardDetailViewModel(
    private val interactor: CardInteractor
) : ViewModel() {

    private val _state = MutableStateFlow(CardDetailState(Async.Loading))
    val state: StateFlow<CardDetailState> = _state

    fun interact(interaction: CardDetailInteraction) {
        when (interaction) {
            is CardDetailInteraction.Opened -> fetchCardList(interaction.name)
        }
    }

    private fun fetchCardList(name: String) = viewModelScope.launch {
        interactor.getCardByName(name)
            .onSuccess {
                _state.update { cardState ->
                    cardState.copy(card = Async.Success(it.first()))
                }
            }
            .onError {
                _state.update { cardState ->
                    cardState.copy(card = Async.Error(it.message))
                }
            }
    }

}