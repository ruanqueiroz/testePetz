package br.com.testpetz.card.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.network.entity.Result
import br.com.testpetz.card.components.scene.Async
import br.com.testpetz.card.domain.usecase.CardInteractor
import br.com.testpetz.card.presentation.CardListFixtures
import br.com.testpetz.card.utils.TestStateRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class CardDetailViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var rule = TestStateRule<CardDetailState>()

    @Mock
    private lateinit var interactor: CardInteractor

    private lateinit var viewModel: CardDetailViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = CardDetailViewModel(interactor)
        rule(viewModel.state)
    }

    @Test
    fun `should show card details when load card data by name`() =  rule.testStates {
        whenever(interactor.getCardByName("name")).thenReturn(Result.Success(listOf(CardListFixtures.cardDummy)))

        viewModel.interact(CardDetailInteraction.Opened("name"))

        verifyExpectedStates(
            CardDetailState(Async.Loading),
            CardDetailState(Async.Success(CardListFixtures.cardDummy))
        )
    }

    @Test
    fun `should show error when load card details`() =  rule.testStates {
        whenever(interactor.getCardByName("name")).thenReturn(Result.Error("error"))

        viewModel.interact(CardDetailInteraction.Opened("name"))

        verifyExpectedStates(
            CardDetailState(Async.Loading),
            CardDetailState(Async.Error("error"))
        )
    }

}