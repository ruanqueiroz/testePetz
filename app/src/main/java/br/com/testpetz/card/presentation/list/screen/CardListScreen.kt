package br.com.testpetz.card.presentation.list.screen

import android.text.Spannable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.text.HtmlCompat
import androidx.navigation.NavHostController
import br.com.testpetz.card.components.scene.Scene
import br.com.testpetz.card.presentation.list.CardInteraction
import br.com.testpetz.card.presentation.list.CardListViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CardListScreen(
    navController: NavHostController,
    viewModel: CardListViewModel = getViewModel()
) {
    LaunchedEffect(Unit) { viewModel.interact(CardInteraction.Opened) }
    val state by viewModel.state.collectAsState()

    Scaffold (
        modifier = Modifier.fillMaxSize(),
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
        ) {
            Scene(
                async = state.tabs,
                error = { CardErrorScreen() },
                loading = {
                    CardLoading()
                }

            ) { list ->
                CardList(cards = list, navController = navController)
            }
        }
    }
}

@Composable
fun CardErrorScreen() {

}

@Composable
fun CardLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(progress = 0.5f)
    }
}

fun String.parse(): Spannable =
    (HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY) as Spannable)