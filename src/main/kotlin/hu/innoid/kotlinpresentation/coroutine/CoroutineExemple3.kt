package hu.innoid.kotlinpresentation.coroutine

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun cancelExample1() {
    val job = Job()
    val scope = CoroutineScope(Dispatchers.Default + job)

    val childJob = scope.launch {
        println("Coroutine started")
        delay(2000)
        println("Coroutine completed")
    }

    runBlocking {
        delay(1000)
        job.cancel() // Cancel the job and its coroutines
        println("Job cancelled")
    }
}


fun superVisorJobExample() {
    val supervisorJob = SupervisorJob()
    val scope = CoroutineScope(Dispatchers.Default + supervisorJob)

    scope.launch {
        println("Child coroutine 1 started")
        delay(1000)
        println("Child coroutine 1 completed")
    }

    scope.launch {
        println("Child coroutine 2 started")
        throw RuntimeException("Error in child coroutine 2")
    }

    runBlocking {
        delay(2000)
        println("Supervisor job isActive: ${supervisorJob.isActive}")
    }
}

fun exceptionHandlerExample() {
    val job = SupervisorJob()
    val scope =
        CoroutineScope(
            Dispatchers.Default
                    + job
                    + CoroutineExceptionHandler { coroutineContext, throwable ->
                println("ERROR: " + throwable.message)
            }
        )

    scope.launch {
        println("Child coroutine 1 started")
        delay(1000)
        println("Child coroutine 1 completed")
    }

    scope.launch {
        println("Child coroutine 2 started")
        throw RuntimeException("Error in child coroutine 2")
    }

    runBlocking {
        delay(2000)
        println("job isActive: ${job.isActive}")
    }
}

fun main() {
    //cancelExample1()
    //superVisorJobExample()
    exceptionHandlerExample()
}
