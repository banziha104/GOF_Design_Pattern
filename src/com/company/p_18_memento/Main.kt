package com.company.p_18_memento

import com.company.p_18_memento.game.Gamer

fun main(args: Array<String>) {
    val gamer = Gamer(100)
    var memento = gamer.createMemento() // 최초 상태를 저장함

    for(i in 0..100){
        println("===== $i")
        println("주인공의 상태 : $gamer")

        gamer.bet()

        println("소지금은 ${gamer.meony} 원이 되었습니다")
        if(gamer.meony > memento.meony){
            println("증가했음으로 현재 상태를 저장")
            memento = gamer.createMemento() // 현재 상태를 저장함
        }else if(gamer.meony < memento.meony / 2){
            println("많이 감소했음으로 이전 상태로 복원")
            gamer.restoreMemento(memento)
        }

        try{
            Thread.sleep(1000)
        }catch (e : Exception){
            e.printStackTrace()
        }

        println()
    }
}