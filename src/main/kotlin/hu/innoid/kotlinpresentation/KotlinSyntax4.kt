package hu.innoid.kotlinpresentation

import kotlin.random.Random

interface Animal {
    val name: String
    fun makeSound()
}

class Cat(override val name: String) : Animal {

    override fun makeSound() {
        println("Miauuu")
    }

}

class Dog(override val name: String) : Animal {
    override fun makeSound() {
        println("Wao Wao")
    }
}

class Monkey : Animal {

    override val name: String
    private val isLoud: Boolean

    constructor(name: String) {
        this.name = name
    }

    constructor(firstName: String, lastName: String) {
        this.name = firstName + lastName
    }

    init {
        isLoud = Random.nextBoolean()
    }

    override fun makeSound() {
        if (isLoud) {
            println("UUUU")
        } else {
            println("uuuu")
        }
    }
}

