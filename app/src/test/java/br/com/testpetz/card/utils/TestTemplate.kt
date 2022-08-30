package br.com.testpetz.card.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import org.junit.Assert

class TestTemplate<T>(private val scope: CoroutineScope) {

    private val emissions = mutableListOf<T>()
    private lateinit var job: Job

    var values: Deferred<List<T>>? = null

    fun observe(target: Flow<T>) {
        job = scope.launch { target.toList(emissions) }
    }

    fun then(asserts: (List<T>) -> Unit) {
        asserts.invoke(emissions)
        job.cancel()
    }

    fun verifyExpectedStates(vararg expectedStates: T) {
        expectedStates.mapIndexed { index, value ->
            Assert.assertEquals(value, emissions[index])
        }
    }

    fun verifyExpectedStatesGivenPrevious(firstState: T, vararg followingStates: (T) -> T) {
        var previousState: T? = null
        val expectedStates = listOf(firstState).plus(
            followingStates.mapIndexed { index, function ->
                (if (index == 0) function.invoke(firstState) else function.invoke(previousState!!))
                    .also { previousState = it }
            }
        )
        Assert.assertEquals(expectedStates, emissions)
    }

    fun cancel() = job.cancel()
}
