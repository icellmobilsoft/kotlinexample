package hu.innoid.kotlinpresentation.generics

class Box<T>(val value: T)

fun <T> singletonList(item: T): List<T> {
    return listOf(item)
}

inline fun <reified T> displayClassName(value: T) {
    println(T::class.java.name)
}

interface Source<out T> {
    fun nextT(): T
}

fun demo(source: Source<String>) {
    val obj: Source<Any> = source  // This is OK, since Source is covariant in T
}

interface Comparable<in T: Number> {
    fun compareTo(other: T): Int
}

fun demo(comparable: Comparable<Number>) {
    val intComparable: Comparable<Int> = comparable  // This is OK, since Comparable is contravariant in T
}



fun main() {
    val intBox = Box(1)
    val stringBox = Box("Hello")
    println(intBox.value)  // Output: 1
    println(stringBox.value)  // Output: Hello

    val list = singletonList(42)
    println(list)  // Output: [42]

    displayClassName("Hello")
}
