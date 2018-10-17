package com.company.p_10_strategy

fun main(args: Array<String>) {
    val player1 = Player("하나",WinningStrategy(1))
    val player2 = Player("두리",WinningStrategy(2))


    for (i in 0..10000){
        val hand1 = player1.nextHand()
        val hand2 = player2.nextHand()

        when(hand1.isStrongThan(hand2)){
            true -> {
                player1.win()
                player2.lose()
            }

            false ->{
                player1.lose()
                player2.win()
            }

            else -> {
                player1.even()
                player2.even()
            }

        }
    }

}