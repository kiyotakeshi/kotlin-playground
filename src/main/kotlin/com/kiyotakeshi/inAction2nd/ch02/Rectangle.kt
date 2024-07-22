package com.kiyotakeshi.inAction2nd.ch02

class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        // custom accessor
        // property の値を保存する代わりに計算する
        // 以下は custom getter
        // クラスの特性を記述する場合は property を使用する
        // クラスの動作を記述する場合は member function を使用する
        get() {
            return height == width
        }
}

// kotlin ではクラスと関数の import を区別しない
// ファイル内で定義されている全ての宣言(class, function, property)がそのパッケージに配置される
// @see src/test/kotlin/com/kiyotakeshi/inAction2nd/ch02/inner/RectangleKtTests.kt
// ※Java だと class をパッケージ構造に一致する file および directory の構造に配置する
fun createUnitSquare(): Rectangle {
    return Rectangle(1, 1)
}
