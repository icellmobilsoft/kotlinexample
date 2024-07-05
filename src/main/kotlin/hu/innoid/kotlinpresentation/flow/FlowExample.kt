package hu.innoid.kotlinpresentation.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

val dataEmitter = flow<String> {
    delay(1000)
    for (i in 1..5) {
        println("Download data: $i")
        delay(100)
        emit("Data: $i")
    }
}.map { data -> data.reversed() }

fun collectData() {
    runBlocking {
        dataEmitter.collectLatest { data -> println(data) }
    }
}


fun main() {
    runBlocking {
        val stateFlowDataEmitter = dataEmitter.stateIn(CoroutineScope(coroutineContext))
        //CollectLatest, suspend function, nem lép tovább a futás, amíg a flow él!
        launch {
            stateFlowDataEmitter.collectLatest { data ->
                println("Received data: $data")
            }
        }

        launch {
            stateFlowDataEmitter.collectLatest { data ->
                println("Received data2: $data")
            }
        }
    }
}
