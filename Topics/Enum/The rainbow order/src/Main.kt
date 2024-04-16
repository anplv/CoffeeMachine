enum class Rainbow(val color: String) {
    RED("red"),
    ORANGE("orange"),
    YELLOW("yellow"),
    GREEN("green"),
    BLUE("blue"),
    INDIGO("indigo"),
    VIOLET("violet")
}

fun main() {
    val color = readln()
    for (enum in Rainbow.values()) {
        if (enum.color == color) println(enum.ordinal + 1)
    }
}