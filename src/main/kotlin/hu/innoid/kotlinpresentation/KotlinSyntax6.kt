package hu.innoid.kotlinpresentation

interface Printer {
    fun printMessage()
}

class SimplePrinter : Printer {
    override fun printMessage() {
        println("Hello from SimplePrinter")
    }
}

class AdvancedPrinter(printer: Printer) : Printer by printer

fun main() {
    val printer = SimplePrinter()
    val advancedPrinter = AdvancedPrinter(printer)
    advancedPrinter.printMessage()  // Output: Hello from SimplePrinter
}


val lazyValue: String by lazy {
    println("Computed!")
    "Hello"
}

fun main3() {
    println(lazyValue)  // Output: Computed! \n Hello
    println(lazyValue)  // Output: Hello
}
