# Proxy

> 필요할떄 만들기  

<br>

- ### 필요 클래스

| 이름        | 해설                                                                                       | 대응클래스   |
|-------------|--------------------------------------------------------------------------------------------|--------------|
| Subject     | Proxy 역할과 RealSubject 역할을 동일시하기 위한 인터페이스를 결정함                        | Printable    |
| Proxy       | Client 역할의 요구를 할수 있는 만큼 처리하고, 처리할 수 없으면 RealSubject에게 역할을 맡김 | PrinterProxy |
| RealSubject | `대리인`인 Proxy가 감당할 수 없는 일이 발생했을 때 등장하는 `본인`의 역할                  | Printer      |
| Client      | Proxy 패턴을 이용하는 역할                                                                 | Main         |

<br>

- ### 예제 클래스


| 이름         | 해설                                   |
|--------------|----------------------------------------|
| Printer      | 이름있는프린터를 나타내는 클래스       |
| Printable    | Printer와 PrinterProxy 공통의 인스턴스 |
| PrinterProxy | 이름있는 프린터를 나타내는 클래스      |
| Main         | 동작 테스트용 클래스                   |

<br>

- ### 전체 소스

```kotlin
package com.company.p_21_proxy

import kotlin.properties.Delegates

interface Printable{
    fun setPrinterName(name : String)
    fun getPrinterName() : String
    fun print(string: String)
}

// 무거워지는 주제를 담당
class Printer(var name : String) : Printable{

    fun heavyJob(msg : String){ // 무거운 일
        print(msg)
        for (i in 0..5){
            try{
                Thread.sleep(1000)
            }catch (e : InterruptedException){
                e.printStackTrace()
            }
            println(".")
        }
        println("완료")
    }
    override fun setPrinterName(name: String) {
        this.name = name
        heavyJob(name)
    }

    override fun getPrinterName(): String = name

    override fun print(string: String) {
        println("=== $name ===")
        println("string")
    }
}

/***
 * 대리인 역할을 수행하며, Printable 인터페이스를 구현
 * real에 본인을 저장함
 * setPrinterName과 getPrinterName을 여러차례 호출해도 Printer의 인스턴스는 생성되지안ㅇ흠
 */
class PrinterProxy(var name : String) : Printable{
    private lateinit var real : Printer // Printer 인스턴스를 담음

    @Synchronized
    override fun setPrinterName(name: String) {
        if(real != null){
            real.setPrinterName(name) // 본인에게도 이름을 설정함
        }
        this.name = name
    }

    override fun getPrinterName(): String = name

    override fun print(string: String) {
        if(real == null) real = Printer(name)  // 필요할떄 생성함
    }
}

fun main(args: Array<String>) {
    val p : Printable = PrinterProxy("Alice")
    p.setPrinterName("abc")
    p.print("Hello, World")
}
```
