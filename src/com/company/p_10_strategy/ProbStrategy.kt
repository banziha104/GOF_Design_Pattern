package com.company.p_10_strategy

import java.util.*

class ProbStrategy(seed : Int) : Strategy{
    val random = Random(seed.toLong())
    var prevHandValue = 0
    var currentHandValue = 0
    var history = arrayOf(
            arrayOf(1,1,1),
            arrayOf(1,1,1),
            arrayOf(1,1,1)
    )

    override fun nextHand(): Hand {
        val bet = random.nextInt(getSum(currentHandValue))
        var handvalue = 0
        if ( bet < history[currentHandValue][0]){
            handvalue = 0
        } else if( bet < history[currentHandValue][0] + history[currentHandValue][1]){
            handvalue = 1
        }else {
            handvalue = 2
        }
        prevHandValue = currentHandValue
        currentHandValue = handvalue
        return Hand.getHand(handvalue)
    }

    override fun study(win: Boolean) {
        if(win) history[prevHandValue][currentHandValue]++
        else {
            history[prevHandValue][(currentHandValue + 1) % 3]++
            history[prevHandValue][(currentHandValue + 2) % 3]++
        }
    }

    fun getSum(hv : Int) : Int{
        var sum = 0
        for (i in 0..3) sum += history[hv][i]
        return sum
    }
}