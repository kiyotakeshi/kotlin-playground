package com.kiyotakeshi.playground

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class ForEachAndException {

    @Disabled
    @Test
    fun `test missing IDs throw InternalException`() {
        val map = mapOf(1 to "one", 3 to "three", 5 to "five")
        val ids = listOf(1, 2, 3, 4, 5)

        ids.forEach { id ->
            println("execute $id")
            // 例外を出した時点で処理が終わる = execute 3 は出力されない
            map[id] ?: internalError("id $id not found")
        }
    }

    @Test
    fun `test missing IDs throw InternalException(all id executed)`() {
        val map = mapOf(1 to "one", 3 to "three", 5 to "five")
        val ids = listOf(1, 2, 3, 4, 5)

        ids.toSet().subtract(map.keys).let { missingIds ->
            if (missingIds.isNotEmpty()) {
                internalError("ids: $missingIds not found")
            }
        }
    }

    @Test
    fun `test missing IDs throw InternalException(all id executed)2`() {
        val map = mapOf(1 to "one", 3 to "three", 5 to "five")
        val ids = listOf(1, 2, 3, 4, 5)

        ids.toSet().filterNot { map.containsKey(it) }.let { missingIds ->
            if (missingIds.isNotEmpty()) {
                internalError("ids: $missingIds not found")
            }
        }
    }
}

private fun internalError(message: String?, cause: Throwable? = null): Nothing {
    throw InternalException(message, cause)
}

class InternalException(message: String?, cause: Throwable? = null) : RuntimeException(message, cause)
