package com.company.p_10_strategy

import java.util.*

// 이겼을 때의 전략
class WinningStrategy(seed : Int) : Strategy{

    var random = Random(seed.toLong())
    var won = false
    lateinit var prevHand: Hand

    override fun nextHand(): Hand {
        if(!won){
            prevHand = Hand.getHand(random.nextInt(3))
        }
        return prevHand
    }

    override fun study(win: Boolean) {
        won = win
    }
}