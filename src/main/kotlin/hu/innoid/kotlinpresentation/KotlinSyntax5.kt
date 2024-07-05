package hu.innoid.kotlinpresentation

import kotlin.random.Random

data class Student(val name: String, val age: Int)

fun main() {
    val studentA = Student("Béla", 21)
    val studentB = Student("Béla", 21)
    println(studentA == studentB)
    println(studentA)
    println(studentB.copy(age = 55))
}


sealed class UIState {
    data class Success(val students: List<Student>) : UIState()
    data class Error(val exception: Exception) : UIState()
    data object Loading : UIState()
}


fun main2() {
    var state: UIState = if (Random.nextBoolean()) {
        UIState.Success(emptyList())
    } else {
        UIState.Error(RuntimeException())
    }

    when (state) {
        UIState.Loading -> println("Loading")
        is UIState.Error -> println(state.exception)
        //SMART CAST
        is UIState.Success -> println(state.students)
    }
}
