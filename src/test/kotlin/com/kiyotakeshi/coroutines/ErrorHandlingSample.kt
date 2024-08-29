package com.kiyotakeshi.coroutines

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import org.junit.jupiter.api.Test
import kotlin.coroutines.cancellation.CancellationException

/**
 * @author kiyota
 */
class ErrorHandlingSample {

    @Test
    fun errorHandlingSameCoroutine() {
        runBlocking {
            // 同一の CoroutineScope 内であれば try-catch を使用する
            launch {
                // launch 内は非同期で実行される
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
                }
                // coroutine の外に伝搬せず無視されるので catch しなくてもアプリケーションはクラッシュしない
                catch (e: CancellationException) {
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

    @Test
    fun twoCoroutineWithException() {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            println("exception: ${throwable.message}")
        }
        val context = Job() + exceptionHandler
        val scope = CoroutineScope(context)

        // 2つの Coroutine を起動したが、
        // いずれかが失敗したタイミングで他の Coroutine も全てキャンセルされる
        // エラーは親と子の両方に伝搬し一つの Job に紐づいた Coroutine は全てキャンセルされる
        scope.launch {
            delay(500L)
            throw Exception("error")
            delay(500L)
            // これは呼ばれない
            println("complate 1")
        }
        scope.launch {
            delay(1_000L)
            // これも呼ばれない
            println("complate 2")
        }
        Thread.sleep(1_500L)
    }

    @Test
    fun twoCoroutineWithSupervisorJob() {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            println("exception: ${throwable.message}")
        }
        // SupervisorJob を使用することで、
        // Coroutine の互いのキャンセルに干渉しなくなる(他の子 Coroutine をキャンセルしない)
        val context = SupervisorJob() + exceptionHandler
        val scope = CoroutineScope(context)

        scope.launch {
            delay(500L)
            throw Exception("error")
            delay(500L)
            // これは呼ばれない
            println("complate 1")
        }
        scope.launch {
            delay(1_000L)
            // これは呼ばれる
            println("complate 2")
        }
        Thread.sleep(1_500L)
    }

    @Test
    fun supervisorJobChildCoroutineScopeIsJob() {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            println("exception: ${throwable.message}")
        }
        val context = SupervisorJob() + exceptionHandler
        val scope = CoroutineScope(context)

        // child CoroutineScope
        scope.launch {
            // grand child CoroutineScope 1
            // child CoroutineScope から新しい CoroutineScope を作成する時
            // Supervisor Job ではなく Job になる
            launch {
                delay(500L)
                throw Exception("error")
                delay(500L)
                // これは呼ばれない
                println("complate 1")
            }
            // grand child CoroutineScope 2
            launch {
                delay(1_000L)
                // これも呼ばれない
                println("complate 2")
            }
        }
        Thread.sleep(1_500L)
    }

    @Test
    fun supervisorJobChildCoroutineScopeUseSupervisorJob() {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            println("exception: ${throwable.message}")
        }
        val context = SupervisorJob() + exceptionHandler
        val scope = CoroutineScope(context)

        // child CoroutineScope
        scope.launch {
            supervisorScope {
                launch {
                    delay(500L)
                    throw Exception("error")
                    delay(500L)
                    // これは呼ばれない
                    println("complate 1")
                }
                // grand child CoroutineScope 2
                launch {
                    delay(1_000L)
                    // これは呼ばれる
                    println("complate 2")
                }
            }
        }
        Thread.sleep(1_500L)
    }
}
