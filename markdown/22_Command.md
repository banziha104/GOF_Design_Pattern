# Command

> 명령을 커맨드로 표현하기

- 명령을 커맨드로 표현하면 하나의 물건처럼 표현할 수 있음

<br>

- ### 필요클래스

<br>

- ### 예제 클래스

| 이름         | 해설                                      | 패키지    |
|--------------|-------------------------------------------|-----------|
| Command      | 명령을 표현하는 인터페이스                | command   |
| Macrocommand | 복수의 명령을 모은 명령을 표현하는 클래스 | command   |
| DrawCommand  | 점 그리기 명령을 표현하는 클래스          | drawer    |
| Drawable     | 그리기 대상을 표현하는 인터페이스         | drawer    |
| DrawCanvase  | 그리기 대상을 구현하는 클래스             | drawer    |
| Main         | 동작 테스트용 클래스                      | Anonymous | 

<br>

- ### 전체소스

```kotlin
package com.company.p_22_command.command

import java.util.*

// ==================================
// 명령을 표현하기 위한 인터페이스
interface Command{
    fun execute()
}

/***
 * 복수의 명령을 모은 명령
 */
class MacroCommand : Command{
    private val commands = Stack<Command>()
    
    override fun execute() {
        val it = commands.iterator()
        while (it.hasNext()){
            it.next().execute()
        }
    }
    
    fun append(cmd : Command) = if (cmd != this){commands.push(cmd)} else {}
    fun undo() = if (!commands.empty()) commands.pop() else {}
    fun clear() = commands.clear()
}


// ==================================


``` 