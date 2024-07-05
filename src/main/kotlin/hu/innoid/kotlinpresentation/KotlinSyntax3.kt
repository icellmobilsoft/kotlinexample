package hu.innoid.kotlinpresentation

/***
 * Scope functions
 */
data class Person(var name: String, var age: Int)

fun letDemonstration() {
    val person: Person? = Person("John", 30)
    person?.let {
        println("Name: ${it.name}")
        println("Age: ${it.age}")
    }
}

fun withDemonstration() {
    val person = Person("John", 30)
    with(person) {
        name = "Doe"
        age = 25
        println("Name: $name, Age: $age")
    }
}

fun applyDemonstration() {
    val person = Person("John", 30).apply {
        name = "Doe"
        age = 25
    }
    println(person)
}

fun alsoDemonstration() {
    val person = Person("John", 30).also {
        it.name = "Doe"
        it.age = 25
        println("Updated person: $it")
    }
}

fun runDemonstration() {
    val person = Person("John", 30)
    val isAdult = person.run {
        age >= 18
    }
    println("Is adult: $isAdult")
}

