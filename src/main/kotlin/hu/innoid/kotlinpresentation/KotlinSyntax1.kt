package hu.innoid.kotlinpresentation

/***
 * Alap típusok
 */
class SimpleTypeExample {
    val isValid: Boolean = true
    val exampleString: String = "Kotlin!!!"
    val appVersion: Int = 10
    val pi: Double = 3.14
    val piFloat: Float = 3.14f

    val isValidShorter = true
    val exampleStringShorter = "Kotlin!!"
    val appVersionShorter = 10
    val piShorter = 3.14
    val piFloatShorter = 3.14f

    //Describe the type of the list
    val listWithoutType = listOf("Hello", "H")

    /***
     * Functions
     */
    //Show expression body
    fun downloadData(url: String): String {
        return "TESTDATA$url"
    }

    //Default parameter usage
    fun getTicketIds(
        onlyValid: Boolean = true,
        includeFutureTickets: Boolean = false
    ): List<String> {
        return listOf("12", "AB")
    }

}

fun main() {
    val example = SimpleTypeExample()
    example.downloadData(url = "URL")
    example.getTicketIds()
    example.getTicketIds(onlyValid = false)
    example.getTicketIds(includeFutureTickets = false)
    example.getTicketIds(includeFutureTickets = true, onlyValid = true)
}


/***
 * When examples
 */
fun describeProgrammingLanguage(value: String): String {
    return when (value) {
        "Kotlin" -> "This is the best programming language"
        "Java" -> "Slowly I'll be as good as Kotlin because I started copying its features"
        "Javascript" -> "Bloeee"
        else -> "Please provide a programming language"
    }
}

fun whenWithExpression(someData: Any?): String {
    return when (someData) {
        is Boolean -> "I'm a boolean"
        is String -> someData
        null -> "data is null"
        else -> "Data is something different"
    }
}

fun whenWithoutParameter(a: Int, b: Int, c: Int) {
    when {
        a > b && a > c -> println("A is the biggest")
        b > c -> println("B is the biggest")
        else -> println("C is the biggest")
    }
}

fun whenWithInterval(x: Int) {
    when (x) {
        in 1..10 -> print("x is in the range")
        !in 10..20 -> print("x is outside the range")
        else -> print("none of the above")
    }
}
