package hu.innoid.kotlinpresentation.coroutine

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

suspend fun fetchData1(): String {
    delay(2000)
    return "Data from source 1"
}

suspend fun fetchData2(): String {
    return withContext(Dispatchers.Default) {
        println("Fetch data witContext: $coroutineContext")
        delay(1500)
        "Data from source 2"
    }
}

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    // Async műveletek elindítása
    val deferred1: Deferred<String> = async {
        println("Fetch1 started...$coroutineContext")
        fetchData1()
    }
    val deferred2: Deferred<String> = async {
        println("Fetch2 started..")
        fetchData2()
    }

    println("Fetching data... ${System.currentTimeMillis() - startTime}")

    // Await results from both coroutines
    val result1 = deferred1.await()
    val result2 = deferred2.await()

    listOf(deferred1, deferred2).awaitAll() //Listába fűzhetőek és awaitAll-al az összes bevárható.

    println("Results received, time diff: ${System.currentTimeMillis() - startTime}")
    println(result1)
    println(result2)
}
