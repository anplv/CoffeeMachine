import java.util.Scanner

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val title = input.nextLine()
    val author = input.nextLine()
    val pages = input.nextInt()

    class Book {
        val title = ""
        val author = ""
        val pages = 0

        fun description(title: String, author: String, pages: Int): String = "$title by $author has $pages pages"
    }

    val book = Book()
    println(book.description(title, author, pages))
}