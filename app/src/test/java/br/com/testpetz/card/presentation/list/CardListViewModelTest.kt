package br.com.testpetz.card.presentation.list

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
class CardListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var rule = TestStateRule<CardState>()


    @Mock
    private lateinit var interactor: CardInteractor

    private lateinit var viewModel: CardListViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = CardListViewModel(interactor)
        rule(viewModel.state)
    }

    @Test
    fun `should show list with content when load card list`() =  rule.testStates {
        whenever(interactor.fetchCardList()).thenReturn(Result.Success(listOf(CardListFixtures.cardDummy)))

        viewModel.interact(CardInteraction.Opened)

        verifyExpectedStates(
            CardState(Async.Loading),
            CardState(Async.Success(listOf(CardListFixtures.cardDummy)))
        )
    }

    @Test
    fun `should show error when load card list`() =  rule.testStates {
        whenever(interactor.fetchCardList()).thenReturn(Result.Error("error"))

        viewModel.interact(CardInteraction.Opened)

        verifyExpectedStates(
            CardState(Async.Loading),
            CardState(Async.Error("error"))
        )
    }

}