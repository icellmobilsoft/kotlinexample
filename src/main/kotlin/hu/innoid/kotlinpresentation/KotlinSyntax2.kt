package hu.innoid.kotlinpresentation

fun demonstrateWhile() {
    var counter = 0
    while (counter < 10) {
        println(counter)
        counter++
    }

    var counter2 = 0
    do {
        counter2++
        println(counter2)
    } while (counter2 < 10)
}

fun demonstrateFor() {
    for (i in 1..3) {
        println(i)
    }

    //Step, downTo, step
    for (i in 6 downTo 0 step 2) {
        println(i)
    }

    val stringList = listOf("A", "B", "C")
    stringList.forEach { item ->
        println(item)
    }

    for (item: String in stringList) {
        println(item)
    }
}

/***
 * NULL safety
 */
var nonNullString: String = ""
val nullableString: String? = null

fun useNullableObjects(): String? {
    if (nullableString != null) {
        println("nonNullString")
    }

    //Ha nullableString null substring is az lesz és nem fut le a substring hívás
    val nullSubString = nullableString?.substring(10)
    nullSubString?.let { nonNullSubString ->
        println(nonNullSubString)
    }

    return nullSubString
}


/***
 * Collections
 */
val immutableListExample = listOf<String>("A", "B", "C")
val mutableListExample = mutableListOf<String?>("A", "B", "C", null)

fun someExtensionFunctionExample() {
    val reversed = immutableListExample.reversed()
    val sorted = immutableListExample.sorted()
    val isKotlinInTheList  = immutableListExample.any { item -> item == "Kotlin" }
    val isKotlinEverywhere  = immutableListExample.all { item -> item == "Kotlin" }
}
