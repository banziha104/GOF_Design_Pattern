# Observer 

> 관찰하고 전파하기

<br>

- ### 필요 클래스


| 이름             | 해설                                                                                      | 대응 클래스                   |
|------------------|-------------------------------------------------------------------------------------------|-------------------------------|
| Subject          | 관찰되는 대상을 나타내며, Observer 역할을 등록하는 메소드와 삭제하는 메소드를 가지고 있음 | NumberGenerator               |
| ConcreteSubject  | 구체적으로 관찰되는 대상을 표현함, 상태가 변화하면 등록되는 Observer 역할에 전달          | RandomNumberGenerator         |
| Observer         | Subject로 부터 상태를 전달 받음                                                           | Observer                      |
| ConcreteObserver | 구체적인 관찰자 역할로, update가 호출되면 그 메소드안에서 Subject 역할의 현재 상태를 취득 | DigitObserver , GraphObserver |


<br>

- ### 예제 클래스

| 이름                  | 해설                                     |
|-----------------------|------------------------------------------|
| Observer              | 관찰자를 나타내는 인터페이스             |
| NumberGenerator       | 수를 생성하는 오브젝트를 나타내느 클래스 |
| RandomNumberGenerator | 랜덤으로 수를 생성하는 클래스            |
| DigitObserver         | 숫자로 수를 표시하는 클래스              |
| GraphObserver         | 간이 그래프로 수를 표시하는 클래스       |
| Main                  | 동작테스트용 클래스                      |

- ### 전체소스

```kotlin
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
```