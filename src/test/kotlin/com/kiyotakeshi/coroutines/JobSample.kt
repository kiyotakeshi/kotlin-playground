package com.kiyotakeshi.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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
}