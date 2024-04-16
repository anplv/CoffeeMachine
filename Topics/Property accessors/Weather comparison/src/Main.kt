const val DEFAULT_DUBAI_TEMPERATURE = 30
const val DEFAULT_MOSCOW_TEMPERATURE = 5
const val DEFAULT_HANOI_TEMPERATURE = 20

fun isPossibleTemperature(degrees: Int): Boolean = degrees in -92..57

class City(val name: String) {
    var degrees: Int = 0
        set(value) {
            when (name) {
                "Dubai" -> field = if (isPossibleTemperature(value)) value else DEFAULT_DUBAI_TEMPERATURE
                "Moscow" -> field = if (isPossibleTemperature(value)) value else DEFAULT_MOSCOW_TEMPERATURE
                "Hanoi" -> field = if (isPossibleTemperature(value)) value else DEFAULT_HANOI_TEMPERATURE
            }
        }
}        

fun main() {
    val first = readln().toInt()
    val second = readln().toInt()
    val third = readln().toInt()
    val firstCity = City("Dubai")
    firstCity.degrees = first
    val secondCity = City("Moscow")
    secondCity.degrees = second
    val thirdCity = City("Hanoi")
    thirdCity.degrees = third

    val cities = listOf(firstCity, secondCity, thirdCity)
    val setOfDegrees = setOf(firstCity.degrees, secondCity.degrees, thirdCity.degrees)
    val minTemperature = setOfDegrees.minOrNull()

    if (setOfDegrees.size < 3) {
        println("neither")
    } else {
        for (city in cities) if (city.degrees == minTemperature) println(city.name)
    }
}