package com.kiyotakeshi.coroutines

import kotlinx.coroutines.delay

/**
 * @author kiyota
 */
class SuspendSample {

    // suspend 修飾子をつけることで、中断可能なメソッドを作成できる
    suspend fun fetchData(): Int {
        delay(1_000)
        return 1
    }
}