package com.kiyotakeshi.coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

/**
 * [com.kiyotakeshi.coroutines.SuspendSample]
 * @author kiyota
 */
class SuspendSampleTest {

    @Test
    fun suspend() {
        runBlocking {
            val suspendSample = SuspendSample()
            // launch, async 内で suspend function の呼び出し
            val job = launch {
                val data = suspendSample.fetchData()
                println(data)
            }
            val deferred = async {
                suspendSample.fetchData()
            }

            job.join()
            val fetchedData = deferred.await()
            println(fetchedData)
        }
    }
}