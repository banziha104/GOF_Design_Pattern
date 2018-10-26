package com.company.p_18_memento.game

// internal 로 game 패키지 외부에서 Memento를 변경할수 없도록함
class Memento(var meony : Int){
    internal var fruits = arrayListOf<String>()
    get() = field.clone() as ArrayList<String>

    internal fun addFruit(fruit : String) = fruits.add(fruit)
}