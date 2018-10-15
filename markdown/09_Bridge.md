# Bridge 패턴

> 기능 계층과 구현 계층 분리

- 기능의 클래스 계층과 구현의 클래스 계층을 나눈다.
- 상속은 기능의 클래스 계층 (기능 확장)
- 구현은 구현의 클래스 계층 (기능 구현)

<br>

---

- ### 필요 클래스


| 이름                  | 해설                                                                                                     | 대응클래스        |
|-----------------------|----------------------------------------------------------------------------------------------------------|-------------------|
| Abstaction            | 기능의 클래스 계층의 최상위 클래스로, Implementor 역할의 메소드를 사용해서 기본적인 기능만 기술된 클래스 | Display           |
| RefinedAbtaction      | Abstaction 역할에 대해 기능을 추가한 역할.                                                               | CountDisplay      |
| Implementor           | 구현 클래스 계층의 최상위 클래스. Abstaction 역할의 인터페이스를 구현하기 위한 메소드를 규정하는 역할    | DisplayImpl       |
| Concrete Implementor | 구체적인 구현자, Implementor 역할의 인터페이스를 구체적으로 구현하는 역할                                | StringDisplayImpl |
| Main                  | 실행                                                                                                     |          

<br>

- ### 예제 클래스 

| 이름              | 해설                                              | 대응클래스 |
|-------------------|---------------------------------------------------|------------|
| Display           | 표시한다는 클래스                                 | Abstaction           |
| CountDisplay      | 지정한 횟수만큼 표시한다는 기능을 `추가한` 클래스 | RefinedAbtaction           |
| DisplayImpl       | 표시한다는 클래스                                 | Implementor           |
| StringDisplayImpl | 문자열을 사용해서 표시한다는 `구현한` 클래스      | Concrete Implementor           |
| Main              | 실행                                              |            | 

###

<br>

- Display : 기능의 최상위 역할

```kotlin

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
```

- DisplayImpl : 구현의 최상위 역할

```kotlin

abstract class DisplayImpl{
    abstract fun rawOpen()
    abstract fun rawPrint()
    abstract fun rawClose()
}

```

- CountDisplay : 상위 기능에 기능을 `추가`한 클래스 

```kotlin

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

```

- StringDisplayImpl : 실제 구현이 일어나는 부분

```kotlin

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

```

- Main : 실행

```kotlin


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

```

