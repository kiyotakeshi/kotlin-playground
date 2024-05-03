## Kotlin Coroutines


- Coroutines を使うことで非同期処理を簡単に実現できる

- 同期処理だと処理に時間がかかる作業だと、それぞれに待ちが発生する

```kotlin
fun main() {
    val data = fetchDate() // サーバからデータを取得
    val transformed = transform(data) // データを加工
    save(transformed) // データを保存
}
```

- Coroutines 以外の手法と問題点
  - thread による処理は数が増えた時に管理が複雑に
  - callback で引数に終了時の処理を定義するのは記述量が増える、処理が上から順に追えない callback hell になる
  - RxKotlin(`Features/Promises/Rx`) は operator を把握しておく必要がある

```kotlin
// RxKotlin approach
fun main() {
    fetchDate()
        .concatMap { transform(it) }
        .concatMapComplatable { save(it) }
        .doOnError { println("error: $it") }
}
```

- Coroutines は非同期で扱いたいメソッドに対して `suspend` 修飾子をつけるだけで実現できる
  - 同期処理に近い形で記述できる

```kotlin
// Coroutines approach
suspend fun main() {
    val data = fetchDate()
    val transformed = transform(data)
    save(transformed)
}
```

- Kotlin Coroutines は単一のスレッドを再利用する
  - `delay` 状態になると他の Coroutines を同じスレッドで実行する(並行に動作する)

---

- CoroutineScope には必ず一つの CoroutineContext が紐づく
  - CoroutineContext はスレッドの指定やキャンセル、エラーハンドリング等の Coroutine の挙動を定義できる

- CoroutinesContext の代表的なもの
  - [Job](./JobSample.kt)
  - [CoroutineDispatcher](./DispatcherSample.kt)
  - [CoroutineName](./DispatcherSample.kt)
  - [CoroutineExceptionHandler](./DispatcherSample.kt)
