package com.kiyotakeshi.coroutines

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test

/**
 * @author kiyota
 */
class AsyncErrorHandlingSample {

    @Test
    fun asyncErrorHandling() {
        val context = Job()
        val scope = CoroutineScope(context)
        val deferred = scope.async {
            // 同一 CoroutineScope 内であれば、
            // async も launch 同様に try-catch でハンドリングできる
            try {
                delay(1_000L)
                throw Exception("error")
            } catch (e: Throwable) {
                println("catch error: ${e.message}")
            }
        }

        scope.launch {
            println("result: ${deferred.await()}")
        }
        Thread.sleep(2_000L)
    }

    @Test
    fun throwErrorWhenAwait() {
        val context = Job()
        val scope = CoroutineScope(context)
        // root CoroutineScope から直接 async を呼び出す場合、
        // await のタイミングでエラーがスローされる
        val deferred = scope.async {
            delay(1_000L)
            throw Exception("error")
        }
        scope.launch {
            try {
                // await のタイミングで try-catch でハンドリングできる(CoroutineExceptionHandler の作成をしなくても良い)
                println("result: ${deferred.await()}")
            } catch (e: Throwable) {
                println("catch error: ${e.message}")
            }
        }
        Thread.sleep(2_000L)
    }

    @Test
    fun catchOutside() {
        val context = Job()
        val scope = CoroutineScope(context)
        scope.launch {
            try {
                coroutineScope {
                    val deferred = scope.async {
                        delay(1_000L)
                        throw Exception("error")
                    }
                    println("result: ${deferred.await()}")
                }
            } catch (e: Throwable) {
                println("catch error: ${e.message}")
            }
        }
        Thread.sleep(2_000L)
    }
}