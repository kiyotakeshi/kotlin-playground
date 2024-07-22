> In Kotlin, if is an expression(式), not a statement(文). 

> Making function definitions more concise by **using expression bodies**

---
- 関数が単一の expression(式) で構成されている場合、中括弧と return 文を省略できる

> its body consists of a single expression (if (a > b) a else b),
> you can use that expression as the entire body of the function, removing the curly braces and the return statement.
> Instead, you can place the single expression right after an equals sign (=):

> 関数の本体は単一の式 ( if (a > b) a else b) で構成されているため、中括弧とステートメントを削除して、その式を関数の本体全体として使用できます
> return。代わりに、単一の式を等号 ( =) の直後に配置することができます。

---
> Kotlin doesn’t differentiate between checked and unchecked exceptions

> 他の多くの最新の JVM 言語と同様に、Kotlin はチェック例外と非チェック例外を区別しません。
> 関数によってスローされる例外を指定せず、例外を処理するかどうかは任意です。この設計上の決定は、Java でチェック例外を使用する慣行に基づいています。経験上、Java のルールでは例外を再スローまたは無視するために多くの無意味なコードが必要になることが多く、ルールでは発生する可能性のあるエラーから一貫して保護されないことがわかっています。