package com.company.p_10_strategy

// 전략의 내부상태를 바꿈
class Player(name : String, val strategy: Strategy){
    var wincount = 0
    var gamecount = 0
    var losecount =0

    fun nextHand() = strategy.nextHand()
    fun win(){
        strategy.study(true)
        wincount ++
        gamecount ++
    }

    fun lose(){
        strategy.study(false)
        losecount++
        gamecount++
    }

    fun even() {
        gamecount++
    }

}