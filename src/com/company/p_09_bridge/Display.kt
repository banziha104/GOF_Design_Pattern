package com.company.p_09_bridge

// 기능 계층의 최상위에 있는 클래스
// displayImpl는 구현 계층의 최상위에 있는 클래스
open class Display(private val impl: DisplayImpl){
    fun open()  = impl.rawClose()
    fun print() = impl.rawPrint()
    fun close() = impl.rawClose()

    fun display(){
        open()
        print()
        close()
    }
}