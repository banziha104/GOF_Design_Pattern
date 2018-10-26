package com.company.p_17_observer

import java.util.*
import kotlin.properties.Delegates

// 관찰자를 표현하는 인터페이스이고, 구체적인 관찰자는 이 인터페이스를 구현함
interface Observer{
    fun update(generator : NumberGenerator)
}

// 수를 생성하는 추상클래스
abstract class NumberGenerator{
    private val observers = arrayListOf<Observer>()

    fun addObserver(observer: Observer){
        observers.add(observer)
    }

    fun deleteObserver(observer: Observer){
        observers.remove(observer)
    }

    fun notifyObservers(){ // Observer 전원에 대해서 갱신시 알림
        val it = observers.iterator()
        while (it.hasNext()){
            (it.next()).update(this)
        }
    }

    abstract fun getNumber() : Int
    abstract fun execute()
}

// 구체적인 생성자
class RandomNumberGenerator : NumberGenerator(){
    private val random = Random()
    var num : Int? = null

    override fun getNumber(): Int = num!!

    override fun execute() {
        for ( i in 0..20){
            num = random.nextInt(50)
            notifyObservers() // 다른 옵저버에게 알림
        }
    }
}

// 숫자로 표현하는 옵저버

class DigitObserver : Observer{
    override fun update(generator: NumberGenerator) {
        println("DigitObserver ${generator.getNumber()}")
        try { Thread.sleep(100) }
        catch (e : Exception){ e.printStackTrace()}
    }
}

// 간이 그래프로 나타냄
class GraphObserver : Observer{
    override fun update(generator: NumberGenerator) {
        println("GraphObserver:")
        var count = generator.getNumber()
        for ( i in 0..count) print("*")
        println()
        try {
            Thread.sleep(100)
        }catch (e : Exception){
            e.printStackTrace()
        }
    }
}

fun main(args: Array<String>) {
    val generator = RandomNumberGenerator()
    val observer1 = DigitObserver()
    val observer2 = GraphObserver()

    generator.addObserver(observer1)
    generator.addObserver(observer2)
    generator.execute()
}