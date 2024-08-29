package com.kiyotakeshi.playground

class Person2 {
    // ある property の値を保持するデータストレージ(この文脈だとメンバー変数)を
    // backing field と呼ぶ
    private var _text: String = ""

    // 公開してる property
    var name: String
        get() {
            // 内部ではメンバー変数 _text を使用
            return _text
        }
        set(value) {
            _text = value
        }
}

class Person3 {
    // backing field を明示的に使わない書き方
    var name: String = ""
        get() {
            // backing field の代わりに field にアクセスする
            return field
        }
        set(value) {
            // recursive property accessor call になる
            // java.lang.StackOverflowError
            // 	at com.kiyotakeshi.playground.Person3.setName(BackingField.kt:27)
            // 	at com.kiyotakeshi.playground.Person3.setName(BackingField.kt:27)
            // 	at com.kiyotakeshi.playground.Person3.setName(BackingField.kt:27)
            // name = value
            // field は kotlin が name property のために生成してくれる backing field
            field = value
        }
}

class Person4 {
    // getter, setter で特別な処理をしていないので以下の記載でも同じ
    var name: String = ""
}
