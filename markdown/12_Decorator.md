# Decorator

> 장식과 내용물 동일시 하기 

<br>

- ### 필요클래스

 이름              | 해설                                                                                           | 대응 클래스            |
|-------------------|------------------------------------------------------------------------------------------------|------------------------|
| Component         | 기능을 추가할 때 핵심이 되는 역할로, 추상적인 장식하기 전의 클래스                             | Display                |
| ConcreteDisplay   | 구체적인 장식전의 클래스                                                                       | ConcreteComponent      |
| Decorator         | Component와 동일한 인터페이스를 가지며, Decorator 역할이  장식할 Component 역할도 가지고 있음. | Border                 |
| ConcreteDecorator | 구체적인 Decorator 역할                                                                        | SideBorder, FullBorder |

<br>

- ### 예제 클래스

| 이름          | 해설                                  |
|---------------|---------------------------------------|
| Display       | 문자열 표시용의 추상클래스            |
| StringDisplay | 1행으로 구성된 문자열 표시용의 클래스 |
| Border        | '장식'을 나타내는 클래스              |
| SideBorder    | 좌우에 장식을 붙이는 클래스           |
| FullBorder    | 상하좌우에 장식을 붙이는 클래스       |
| Main          | 동작 테스트용의 클래스                |

<br>


- ### 전체 소스

```kotlin
package com.company.p_12_decorator

// 실행
fun main(args: Array<String>) {
    val b1 = StringDisplay("Hello, World")
    val b2 = SideBorder(b1, '#')
    val b3 = FullBorder(b1)

    b1.show()
    b2.show()
    b3.show()

    val b4 = SideBorder(
            FullBorder(
                    FullBorder(
                            SideBorder(
                                    FullBorder(
                                            StringDisplay("안녕하세요")
                                    ), '*')
                    )
            ), '/')
    
    b4.show()

}

abstract class Display {
    abstract fun getColumns(): Int
    abstract fun getRows(): Int
    abstract fun getRowText(row: Int): String
    fun show() {
        for (i in 0..getRows()) println(getRowText(i))
    }
}

class StringDisplay(val str: String) : Display() {
    override fun getColumns(): Int = str.toByteArray().size

    override fun getRows(): Int = 1

    override fun getRowText(row: Int): String {
        if (row == 0) return str
        else return ""
    }
}

// 장식을 나타내는 추상클래스 , 상속에 의해 장식은 내용물과 동일한 메소드를 가짐
abstract class Border(display: Display) : Display() // 장식을 둘러싸고 있는 내용물

// 구체적인 장식 클래스
class SideBorder(val display: Display,  // 생성자에 서 Display라는 장식 문자를 지정
                 val char: Char) : Border(display) { // char는 장식이되는 문자
    override fun getColumns(): Int = 1 + display.getColumns() + 1

    override fun getRows(): Int = display.getRows()

    override fun getRowText(row: Int): String = char + display.getRowText(row) + char // 양쪽에 더함
}

// Border 하위 클래스 중 하나

class FullBorder(val display: Display) : Border(display) {
    override fun getColumns(): Int = 1 + display.getColumns() + 1

    override fun getRows(): Int = 1 + display.getRows() + 1

    override fun getRowText(row: Int): String = when (row) {
        0 -> "+ ${makeLine('-', display.getColumns())} +"
        display.getRows() + 1 -> "+ ${makeLine('-', display.getColumns())} +"
        else -> "| ${display.getRowText(row - 1) + "|"}"
    }


    private fun makeLine(ch: Char, count: Int): String {
        val buf = StringBuffer()
        for (i in 0..count) {
            buf.append(ch)
        }
        return buf.toString()
    }

}


```