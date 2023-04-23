package com.kiyotakeshi.coroutines

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test

/**
 * @author kiyota
 */
internal class JobSample {

    @Test
    fun job() {
        runBlocking {
            val job1 = launch {
                delay(100L)
                println("completed: job1")
            }
            val job2 = launch {
                delay(100L)
                println("completed: job2")
            }
            // join で job が完了するまで(両方の coroutine が完了するまで)待つ
            job1.join()
            job2.join()
            println("completed: all")
        }
    }

    @Test
    fun jobCancel() {
        runBlocking {
            val job1 = launch {
                println("start: job1")
                delay(100L)
                // これは呼ばれない
                println("completed: job1")
            }
            val job2 = launch {
                println("start: job2")
                delay(100L)
                println("completed: job2")
            }
            delay(50L)

            // 個別に coroutine をキャンセルできる
            job1.cancel()
        }
    }

    @Test
    fun coroutineScopeWithJob() {
        // val context: CompletableJob = Job()
        val context = Job()

        // Job も CoroutineContext であり、 CoroutineScope の作成時に指定できる
        val scope1 = CoroutineScope(context)
        scope1.launch {
            println("start: scope1")
            delay(1_000L)
            println("completed: scope1")
        }

        val scope2 = CoroutineScope(context)
        scope2.launch {
            println("start: scope2")
            delay(1_000L)
            println("completed: scope2")
        }

        Thread.sleep(500L)
        // 対象の Job を使用している coroutine を同時にキャンセルする
        context.cancel()
        Thread.sleep(2_000L)
    }
}