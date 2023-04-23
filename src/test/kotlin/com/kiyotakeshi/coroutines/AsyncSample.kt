package com.kiyotakeshi.coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

/**
 * @author kiyota
 */
class AsyncSample {

    @Test
    fun async() {
        runBlocking {
            launch {
                // deferred = 延期された
                // val deferred1: Deferred<Int> = async {
                val deferred1 = async {
                    delay(100L)
                    1
                }
                val deferred2 = async {
                    delay(100L)
                    2
                }
                // await することで値を取得することできるので、
                // 複数の coroutines を待ち合わせて結果の値を利用できる
                println(deferred1.await() + deferred2.await())
            }
        }
    }
}