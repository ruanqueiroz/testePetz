package br.com.testpetz.card.components.scene

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun <T> Scene(
    async: Async<T>,
    loading: @Composable () -> Unit = { CircularProgressIndicator(progress = 0.5f) },
    error: @Composable (String) -> Unit,
    content: @Composable (T) -> Unit,
) {
    MaterialTheme {
        when (async) {
            is Async.Error -> error(async.message)
            Async.Loading -> loading()
            is Async.Success -> content(async.value)
        }
    }
}
