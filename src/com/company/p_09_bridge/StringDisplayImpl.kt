package com.company.p_09_bridge

// 실제 구현 클래스
// 기능을 구현을 실제로 담당함
class StringDisplayImpl(val string: String) : DisplayImpl(){
    private var width = string.length
    override fun rawOpen() {
        printLine()
    }

    override fun rawPrint() {
        println("| $string |")
    }

    override fun rawClose() {
        println()
    }

    fun printLine(){
        println("+")
        for (i in 0..width){
            println("-")
        }
        print("+")
    }
}