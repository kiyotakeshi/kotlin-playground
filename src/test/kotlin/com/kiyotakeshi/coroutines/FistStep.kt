package com.kiyotakeshi.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.coroutines.EmptyCoroutineContext

/**
 * @author kiyota
 */
internal class FistStep {

    @Test
    fun coroutines() {
        // Coroutines を起動するには CoroutineScope が必要
        // runBlocking creates new CoroutineScope and blocks the current thread until all coroutines in this scope complete
        // `runBlocking` は起動された全ての Coroutines が完了するまで動作をブロックするため実際のコードでは使われることはあまりない
        runBlocking {
            // Coroutines を起動
            // every launch creates new coroutine and runs it concurrently
            launch {
                println(1)
                delay(1_000L)
                println(2)
            }
            launch {
                println(3)
            }
        }
    }

    @Test
    fun emptyCoroutineContext() {
        val scope = CoroutineScope(EmptyCoroutineContext)
        scope.launch {
            println(1)
            delay(1_000L)
            println(2)
        }
        scope.launch {
            println(3)
        }
        // `EmptyCoroutineContext` は意味を持たない空の Context のため、完了を待ってくれない
        // そのため、`Thread.sleep` で待つ必要がある(待たないと 2 は表示されない)
        Thread.sleep(2_000L)
    }

    @Test
    fun cancelCoroutineScope() {
        val scope = CoroutineScope(EmptyCoroutineContext)
        scope.launch {
            delay(1_000L)
            println(1)
        }
        scope.launch {
            delay(1_000L)
            println(2)
        }
        // CoroutineScope を指定して cancel することで、起動していた coroutine は全てキャンセルされる
        // view が破棄されたタイミングで全ての coroutine をキャンセルする、といった使い方ができる
        scope.cancel()
    }

    @Test
    fun cancelCoroutineScope2() {
        val scope = CoroutineScope(EmptyCoroutineContext)
        // child coroutine(create new CoroutinesScope)
        scope.launch {
            print(1)
            // grand child coroutine
            launch {
                println(2)
            }
        }
        // child, grand child どちらもキャンセルされる
        scope.cancel()
    }
}
