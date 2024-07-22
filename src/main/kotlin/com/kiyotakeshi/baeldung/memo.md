https://www.baeldung.com/kotlin/intro

> Inheritance
> By default, Kotlin’s classes are closed for extension — the equivalent of a class marked final in Java.
> In order to specify that a class is open for extension, you would use the open keyword when defining the class.

> Exceptions
> Every exception in Kotlin is unchecked, meaning that compiler does not force us to catch them.

> Null Safety

```kotlin
fun findItemNameForId(id: String): Item
```

> then calling code will not need to handle a null case because it is guaranteed by the compiler and Kotlin language, that returned object can not be null.

> Data classes
> A very nice language construct that could be found in Kotlin is data classes
> The purpose of such classes is to only hold data

> The compiler will create for us methods hashCode(), equals(), and toString().
> It is good practice to make data classes immutable, by using a val keyword.
> Data classes could have default field values:

```kotlin
data class Item(val id: String, val name: String = "unknown_name")
```

> Extension Function
> Suppose that we have a class that is a part of 3rd party library, but we want to extend it with an additional method.
> Kotlin allows us to do this by using extension functions.



