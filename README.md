# kotlin-playground

## memo

- Kotlin は JavaScript にコンパイルすることも可能で、 Web ブラウザ内で Kotlin のコードを実行可能

- Kotlin は関数型プログラミングをサポート
  - function type
  - lambda expression
  - data class

- 関数型プログラミングの概念
  - first-class function(第一級関数)
    - 関数を値として扱い、変数に保持したり別の関数の戻り値として返せる
  - immutability
    - 生成後に変更できないことが保証されている不変なオブジェクトを扱う
  - 副作用がない
    - 同じ入力を与えたら同じ結果を返す
    - 他のオブジェクトの状態を変更しない

- 関数型プログラミングのメリット
  - 安全なマルチスレッド
  - テストが簡単(切り離して個別にテストできる)

- コードが単純で簡潔であるほど何を意図したコードであるかが早く理解できる、というスタンスみたい
  - 言語の構文がコードの意図を明確に表現するために、コードの構造上の都合のために書かれるコードがないように努めた
    - getter,setter, constructor の引数をフィールドに代入するコードなどのボイラープレートが暗黙的でコードを散らかさない
  - コレクション内の要素を見つける、などのよくある処理に関するコードが不用意に長くならないように努めた
    - ライブラリ内のメソッド呼び出しに書き換えられるように

- NullPointerException を無くすように努めている
    - ランタイム時に null になりえる値となりえない値を検出する

- ClassCastException を防いでいる
  - 型のチェックとキャストが1つの操作に統合されている

- Kotlin のコンパイラは .class ファイルを生成する

- 関数を定義する際に用いられる変数を parameter(仮引数)、呼び出す際に渡す値を argument(実引数)と呼ぶ

- 基本的には Java のディレクトリ構成に従って配置すると良い
  - ただしクラスが小さい時は複数のクラスを同じファイルに含めても問題ない

- top level functions does not belong to a class(in Java, functions can only be part of class)
  - In Kotlin, top level functions can be part of a .kt file(not a class)

- top level properties does not belong to class

- clas in OOP is fundamentally the blueprint for creating objects 

- kotlin does not have the support for the static keyword
  - companion object can be used to introduce static functionalities

- kotlin does not have checked exceptions
