# Flyweight

> 공유를 통해 낭비를 없에기

<br>

- ### 필요클래스

| 이름             | 해설                                                                                          | 대응클래스     |
|------------------|-----------------------------------------------------------------------------------------------|----------------|
| Flyweight        | 공유하는 것이 좋을 것 같은 클래스                                                             | Bigchar        |
| FlyweightFactory | Flyweight 역할의 인스턴스들을 공유하며, 있는 경우 기존 인스턴스를, 없는 경우 새로 만드는 역할 | BigCharFactory |
| Client           | 의뢰자 역할로 Flyweight 역할을 만들고 그것을 이용함                                           | BigString      |

<br>

- ### 예제클래스

| 이름           | 해설                                              |
|----------------|---------------------------------------------------|
| BigChar        | 큰문자를 나타내는 클래스                          |
| BigCharFactory | BigChar의 인스턴스를 공유하면서 생성하는 클래스   |
| BigString      | BigChar를 모아서 만든 큰 문자열을 나타내는 클래스 |
| Main           | 동작테스트용 클래스                               |

<br>

- ### 전체코드

```kotlin
package com.company.p_20_flyweight

import java.io.BufferedReader
import java.io.FileReader
import kotlin.coroutines.experimental.buildIterator

class BigChar(val char : Char){
    init {
        try {
            val reader = BufferedReader(FileReader("big$char.txt"))
            val buf = StringBuffer()
            var line = ""
            reader.useLines {
                buf.append(line)
                buf.append("\n")
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }
    
    fun print() = print(char)
}

/***
 * 핵심적인 클래스로, 기존에 만들어진 인스턴스를 관리한다
 */
object BigCharFactory{
    // 이미 만들어진 BigChar의 인스턴스를 관리
    private val pool = HashMap<String,BigChar>()

    /***
     * 만약 기존 객체가 존재하면 해당 객체를 해쉬맵에서 가져옮
     * 만약 없다면 해쉬맵에 넣음 
     * 이를 통해 공유하여 자원낭비를 줄임
     */
    @Synchronized
    fun getBigChar(char : Char) : BigChar{
        var bc = pool["$char"]
        if(bc == null){
            bc = BigChar(char)
            pool[""+char] = bc
        }
        return bc
    }
}

class BigString(var string: String){
    private var bigchar = arrayOf<BigChar>()
    init {
        for ( i in 0..bigchar.size) BigCharFactory.getBigChar(string.first())
    }
    
    fun print(){
        for(i in bigchar) i.print()
    }
}

fun main(args: Array<String>) {
    val bs = BigString(args[0])
    bs.print()
}
```