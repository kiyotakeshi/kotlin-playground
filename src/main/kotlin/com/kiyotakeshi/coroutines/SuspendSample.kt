package com.kiyotakeshi.coroutines

import kotlinx.coroutines.delay

/**
 * @author kiyota
 */
class SuspendSample {

    @Suppress("MagicNumber")
    // suspend 修飾子をつけることで、中断可能なメソッドを作成できる
    suspend fun fetchData(): Int {
        // suspend functions ないで別の suspend functions を呼び出すことができる
        delay(1_000)
        return 1
    }
}
