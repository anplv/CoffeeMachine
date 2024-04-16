package machine

import machine.State.*

enum class Coffee(val water: Int, val milk: Int, val beans: Int, val price: Int) {
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6),
}

enum class State { DEFAULT, BUY, FILL, TAKE, REMAINING, EXIT }

class CoffeeMachine(
    private var water: Int,
    private var milk: Int,
    private var beans: Int,
    private var cups: Int,
    private var cash: Int) {

    var state = DEFAULT

    fun process() {
        state = when (state) {
            DEFAULT -> chooseAction()
            BUY -> buy()
            FILL -> fill()
            TAKE -> takeCash()
            REMAINING -> getRemaining()
            else -> throw RuntimeException("Unknown state")
        }
        println()
    }

    private fun chooseAction(): State {
        print("Write action (buy, fill, take, remaining, exit): ")

        return when (readln().lowercase().trim()) {
            "buy" -> BUY
            "fill" -> FILL
            "take" -> TAKE
            "remaining" -> REMAINING
            "exit" -> EXIT
            else -> {
                println("Unknown action")
                DEFAULT
            }
        }
    }

    private fun getRemaining(): State {
        println("The coffee machine has:\n" +
                "$water of water\n" +
                "$milk of milk\n" +
                "$beans of coffee beans\n" +
                "$cups of disposable cups\n" +
                "$cash of money")
        return DEFAULT
    }

    private fun buy(): State {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
        val userInput = readln()

        if(userInput.lowercase().trim() == "back") return DEFAULT

        val coffeeType = Coffee.values()[userInput.toInt() - 1]
        when {
            water < coffeeType.water -> {
                println("Sorry, not enough water!")
            }
            milk < coffeeType.milk -> {
                println("Sorry, not enough milk!")
            }
            beans < coffeeType.beans -> {
                println("Sorry, not enough coffee beans!")
            }
            cups < 1 -> {
                println("Sorry, not enough disposable cups!")
            }
            else -> {
                makeCoffee(coffeeType)
                println("I have enough resources, making you a coffee!")
            }
        }
        return DEFAULT
    }

    private fun makeCoffee(coffeeType: Coffee) {
        water -= coffeeType.water
        milk -= coffeeType.milk
        beans -= coffeeType.beans
        cups--
        cash += coffeeType.price
    }

    private fun fill(): State {
        println("Write how many ml of water you want to add:")
        water += readln().toInt()
        println("Write how many ml of milk you want to add:")
        milk += readln().toInt()
        println("Write how many grams of coffee beans you want to add:")
        beans += readln().toInt()
        println("Write how many disposable cups you want to add:")
        cups += readln().toInt()
        return DEFAULT
    }

    private fun takeCash(): State {
        println("I gave you $${cash}")
        cash = 0
        return DEFAULT
    }
}


fun main() = CoffeeMachine(400, 540, 120, 9, 550).run {
    do process() while (state != EXIT)
}