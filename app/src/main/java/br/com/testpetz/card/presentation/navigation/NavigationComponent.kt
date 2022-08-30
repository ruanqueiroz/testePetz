package br.com.testpetz.card.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.testpetz.card.presentation.detail.screen.CardDetailScreen
import br.com.testpetz.card.presentation.list.screen.CardListScreen

@Composable
fun NavigationComponent(
    modifier: Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "list"
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("list") {
            CardListScreen(navController)
        }
        composable("details/{cardName}") { backStackEntry ->
            backStackEntry.arguments?.getString("cardName")?.let { CardDetailScreen(it) }
        }
    }
}