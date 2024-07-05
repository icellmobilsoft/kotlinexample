package hu.innoid.kotlinpresentation.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun fetchData(timeout: Long): String {
    delay(timeout) // Nem blokkolja az adott thread-t!
    return "Data fetched"
}

// Main function
fun main() {
    val job = Job() // A coroutine aktuális állapotát tartja számon, illetve ezen keresztül módosítható az állapota
    val dispatcher = Dispatchers.Default // Milyen szálon fusson a coroutine
    val coroutineScope = CoroutineScope(dispatcher + job)  //Maga a scope amin futtatjuk a coroutinet.

    // Launch a coroutine in the defined CoroutineScope
    coroutineScope.launch {
        // Itt elérhető az adott coroutine contextusa
        println("Coroutine context: $coroutineContext")

        // Itt már coroutineScope-n belül vagyunk, hívhatjuk a suspend functiont
        val data = fetchData(1000)
        println(data)
    }

    coroutineScope.launch {
        println(fetchData(5000))
    }

    println("Main thread is not blocked!!!")

    // Clean up job
    Thread.sleep(3000)
    job.cancel()
}
