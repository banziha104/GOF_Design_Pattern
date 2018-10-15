package com.company.p_09_bridge

// 기능 계층의 하위 클래스
// 지정한 만큼 표시하는 기능이 추가
class CountDisplay(displayImpl: DisplayImpl) : Display(displayImpl){

    // 새로운 기능 추가
    fun multiDisplay(times : Int){
        open()
        for (i in 0..times) print()
        close()
    }
}