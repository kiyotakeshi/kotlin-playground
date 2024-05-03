package com.kiyotakeshi.coroutines

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test

/**
 * @author kiyota
 */
class DispatcherSample {

    @Test
    fun dispatcherDefault() {
        // JVM ではバックグラウンドスレッドが使用される
        val context = Dispatchers.Default
        val scope = CoroutineScope(context)

        // 現在のスレッドを確認する
        println("thread: ${Thread.currentThread().name}") // thread: Test worker

        scope.launch {
            println("thread: ${Thread.currentThread().name}") // thread: DefaultDispatcher-worker-1 @coroutine#1
        }

        // Main は Android では、UI 操作のためのスレッド
        // CLI で動かす場合はエラーとなる
        // Exception in thread "Test worker" java.lang.IllegalStateException: Module with the Main dispatcher is missing. Add dependency providing the Main dispatcher, e.g. 'kotlinx-coroutines-android' and ensure it has the same version as 'kotlinx-coroutines-core'
//         val mainContext = Dispatchers.Main
//         val mainScope = CoroutineScope(mainContext)
//        mainScope.launch {
//            println("thread: ${Thread.currentThread().name}")
//        }

        Thread.sleep(1_000L)
    }

    @Test
    fun dispatcherIO() {
        // JVM のみで使用できるオプション
        // IOタスク用のスレッド群(`Dispatchers.Default` と一部共有された)から1つ選んで実行する
        val context = Dispatchers.IO
        val scope = CoroutineScope(context)
        println("thread1: ${Thread.currentThread().name}") // thread1: Test worker
        scope.launch {
            println("thread2: ${Thread.currentThread().name}") // thread2: DefaultDispatcher-worker-1 @coroutine#1
        }
        Thread.sleep(1_000L)
    }

    @Test
    fun coroutineName() {
        // デバック時にわかりやすいように CoroutineContext に名前をつける
        val context = Dispatchers.Default + CoroutineName("myCoroutine")
        val scope = CoroutineScope(context)
        scope.launch {
            println("thread: ${Thread.currentThread().name}") // thread: DefaultDispatcher-worker-1 @myCoroutine#1
        }
        Thread.sleep(1_000L)
    }

    @Test
    fun coroutineExceptionHandler() {
        // 同一 Coroutine 内であれば try-catch でエラーハンドリングできる
        // CoroutineExceptionHandler はそこで try-catch で catch されなかったエラーを処理できる
        // root の CoroutineScope のみに適用できる
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            println("exception: ${throwable.message}") // exception: error
        }
        val context = Dispatchers.Default + exceptionHandler
        val scope = CoroutineScope(context)
        scope.launch {
            delay(1_000L)
            throw RuntimeException("error")
        }
        Thread.sleep(2_000L)
    }
}