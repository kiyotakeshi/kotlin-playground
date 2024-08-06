# [scope functions](https://kotlinlang.org/docs/scope-functions.html)

スコープ関数 = オブジェクトのコンテキスト内でコードブロックを実行するための関数。

> to execute a block of code within the context of an object

ラムダ式が指定されたオブジェクトに対してスコープ関数を呼び出すと、一時的なスコープが形成される。

> When you call such a function on an object with a lambda expression provided, it forms a temporary scope.

このスコープでは名前なしでオブジェクトにアクセスできる。

> In this scope, you can access the object without its name

スコープ関数(let, run, with, apply, also)の違いは、
どのようにブロック内でオブジェクトが利用可能かと、式全体の結果がどうなるか。

> What's different is how this object becomes available inside the block and what the result of the whole expression is.


## function selection

let = null でないオブジェクトに対してラムダを実行する
let = 式(expression)をローカルスコープで変数として導入する

> Executing a lambda on non-nullable objects: let

> Introducing an expression as a variable in local scope: let

apply = オブジェクトの構成

> Object configuration: apply

run = オブジェクトの構成と結果の計算
run = 式(expression)が必要なステートメントの実行

> Object configuration and computing the result: run

> Running statements where an expression is required: non-extension run

also = 追加の効果

> Additional effects: also

with = オブジェクトに対する関数呼び出しのグループ化

> Grouping function calls on an object: with

異なるスコープ関数のユースケースが重複しているため、プロジェクトやチームでは規約に基づいて使用する関数を選択できる。
(というより使用する関数について規約を決めた方がいい。)

> The use cases of different scope functions overlap, so you can choose which functions to use based on the specific conventions used in your project or team.


スコープ関数はコードを簡潔にしますが、使いすぎは禁物。コードが読み部落なりエラーに繋がるため。

> Although scope functions can make your code more concise, avoid overusing them: it can make your code hard to read and lead to errors.

スコープ関数の入れ子も避けて、連鎖させる時は注意する。

> We also recommend that you avoid nesting scope functions and be careful when chaining them. 


スコープ関数の違いは、「どのようにコンテキストオブジェクトを参照するか」、「戻り値」 

> There are two main differences between each scope function:

> The way they refer to the context object.
> Their return value.

> どのようにコンテキストオブジェクトを参照するか

context object が this or it

scope function が context object を参照するための方法として、
lambda の receiver(this) を使うか、 lamdba の引数(it) を使うかの違い。

どちらも同じ機能を提供するが、コードの可読性を向上させるために適切なものを選択する。

> 戻り値

apply, also は context object を返す。
let, run, with は lambda の結果を返す。



