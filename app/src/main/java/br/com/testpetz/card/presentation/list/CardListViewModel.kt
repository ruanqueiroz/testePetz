package br.com.testpetz.card.presentation.list

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

class CardListViewModel(
    private val interactor: CardInteractor
) : ViewModel() {

    private val _state = MutableStateFlow(CardState(Async.Loading))
    val state: StateFlow<CardState> = _state

    fun interact(interaction: CardInteraction) {
        when (interaction) {
            is CardInteraction.Opened -> fetchCardList()
        }
    }

    private fun fetchCardList() = viewModelScope.launch {
        interactor.fetchCardList()
            .onSuccess {
                _state.update { cardState ->
                    cardState.copy(tabs = Async.Success(it))
                }
            }
            .onError {
                _state.update { cardState ->
                    cardState.copy(tabs = Async.Error(it.message))
                }
            }
    }

}