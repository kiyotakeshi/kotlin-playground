package com.kiyotakeshi.inAction2nd.ch04

class DelegatingCollection<T> : Collection<T> {
    private val innerList = arrayListOf<T>()

    // Collection のメソッドを全て実装する必要あり
    override val size: Int
        get() = innerList.size

    override fun isEmpty(): Boolean = innerList.isEmpty()

    override fun iterator(): Iterator<T> = innerList.iterator()

    override fun contains(element: T): Boolean = innerList.contains(element)

    override fun containsAll(elements: Collection<T>): Boolean = innerList.containsAll(elements)
}

class DelegatingCollectionBy<T>(
    innerList: Collection<T> = mutableListOf()
) : Collection<T> by innerList
