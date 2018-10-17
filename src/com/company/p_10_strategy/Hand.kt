package com.company.p_10_strategy

class Hand(val value : Int){
    companion object {
        val GUU = 0
        val CHO = 1
        val PAA = 2
        val hand = arrayOf(
                Hand(GUU),
                Hand(CHO),
                Hand(PAA)
        )
        val name = arrayOf(
                "주먹",
                "가위",
                "보"
        )
        fun getHand(handvalue : Int) = hand[handvalue]
    }


    // 비교하는 메소드
    fun fight(h: Hand) : Int{
        if(this == h){
            return 0
        }else if ((this.value +1) % 3 == h.value ){
            return 1
        }else{
            return -1
        }
    }

    fun isStrongThan(h : Hand) : Boolean{
        return fight(h) == 1
    }
    fun isWeakerThan(h : Hand) : Boolean{
        return fight(h) == -1
    }

}