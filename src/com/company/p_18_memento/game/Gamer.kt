package com.company.p_18_memento.game

import java.util.*

class Gamer(
        var meony : Int){
    private var fruits = arrayListOf<String>()
    private val random = Random()
    private val frutisname = arrayOf("사과","포도","바나나","귤")
    fun bet(){
        var dice = random.nextInt(6) + 1
        when(dice){
            1 -> {
                meony += 100
                println("소지금이 증가함")
            }
            2 -> {
                meony /= 2
                println("소지금 절반")
            }
            6 -> {
                val f = getFruit()
                println("과일 ${f}를 받았음")
                fruits.add(f)
            }
            else ->{
                println("변한것이 없음")
            }
        }
    }

    /***
     * 핵심적인 부분
     * 스냅샷을 찍어둠
     */
    fun createMemento() : Memento{
        var m = Memento(meony)
        val it = fruits.iterator()
        while (it.hasNext()) {
            val f = it.next()
            if(f.startsWith("맛있는")) m.addFruit(f)
        }
        return m
    }

    /***
     * 뒤로가기 실행
     * 일종의 부활주문
     */
    fun restoreMemento(memento: Memento){
        this.meony = memento.meony
        this.fruits = memento.fruits
    }

    override fun toString(): String {
        return "[meney + $meony ,fruits = $fruits]"
    }

    private fun getFruit() : String{
        var prefix = ""
        if(random.nextBoolean()) prefix = "맛있는"
        return prefix + frutisname[random.nextInt(frutisname.size)]
    }
}