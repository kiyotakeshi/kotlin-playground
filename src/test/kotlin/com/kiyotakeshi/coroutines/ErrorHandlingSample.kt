package com.kiyotakeshi.coroutines

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test

/**
 * @author kiyota
 */
class ErrorHandlingSample {

    @Test
    fun errorHandlingSameCoroutine() {
        runBlocking {
            // 同一の CoroutineScope 内であれば try-catch を使用する
            launch {
                try {
                    println("start")
                    delay(100L)
                    throw RuntimeException("error")
                    // これは呼ばれない
                    println("end")
                } catch (e: Exception) {
                    println("catch error: $e")
                }
            }
        }
    }

    @Test
    fun errorHandlingCancel() {
        runBlocking {
            val job = launch {
                try {
                    println("start")
                    delay(100L)
                    // これは呼ばれない
                    println("end")
                    // coroutine の外に伝搬せず無視されるので catch しなくてもアプリケーションはクラッシュしない
                } catch (e: CancellationException) {
                    println("catch error: $e")
                } finally {
                    println("finally")
                }
            }
            delay(50L)
            // 中断関数は CancellationException を throw する
            job.cancel()
        }
    }

    @Test
    fun errorHandlingCoroutineScope() {
        val context = Job()
        val scope = CoroutineScope(context)
        scope.launch {
            try {
                // 内部で起動した全ての coroutines の実行完了を待ってくれるため try-catch でエラーを取得できる
                coroutineScope {
                    launch {
                        delay(1_000L)
                        throw RuntimeException("error")
                    }
                }
            } catch (e: Exception) {
                println("catch error: $e")
            }
        }
        Thread.sleep(1_500L)
    }
}