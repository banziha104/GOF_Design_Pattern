# Interpreter 

> 문법 규칙을 클래스로 표현 

<br>

- ### 필요 클래스 

| 이름                  | 해설                                                       | 대응 클래스                 |
|-----------------------|------------------------------------------------------------|-----------------------------|
| AbstractExpression    | 추상적인 표현 역할                                         | Node                        |
| TerminalExpression    | 마지막 종착점을 표현함                                     | PrimitiveNode               |
| NonterminalExpression | 자신이 처리하고 이외는 넘기는 역할                         | ProgramNode, CommandNode 등 |
| Context               | 인터프리터가 구문해석을 실행하기 위한 정보를 제공하는 역할 | Context                     |
| Client                | 의뢰자 역할                                                | Main                        |

<br>


- ### 예제 클래스  


| 이름                 | 해설                                        |
|----------------------|---------------------------------------------|
| Node                 | 구문 트리의 '노드'가 되는 클래스            |
| ProgramNode          | <program> 에 대응하는 클래스                |
| CommandListNode      | <command list>에 대응하는 클래스            |
| CommandNode          | <command>에 대응하는 클래스                 |
| RepeatCommandNode    | <repeat command>에 대응하는 클래스          |
| PrimitiveCommandNode | <primitive command>에 대응하는 클래스       |
| Context              | 구문해석을 위한 전후 간계를 나타내는 클래스 |
| ParseException       | 구문해석 중의 예외 클래스                   |
| Main                 | 동작 테스트용 클래스                        |



```kotlin
package com.company.p_23_interpreter

import java.text.ParseException
import java.util.*
import kotlin.collections.ArrayList

// 구문 트리의 각 부분을 구성하는 최상위 클래스
abstract class Node{
    // 구문 해석
    abstract fun parse(context : Context)
}

class ProgramNode : Node(){
    var commandListNode : Node? = null
    override fun parse(context: Context) {
        context.skipToken("program")
        commandListNode = CommandListNode()
        commandListNode?.parse(context)
    }
}

class CommandListNode : Node() {
    val list = arrayListOf<Node>()
    override fun parse(context: Context) {
        loop@while (true) {
            if (context.currentToken() == null) {
                throw ParseException("", 0)
            } else if (context.currentToken() === "end") {
                context.skipToken("end");
                break@loop
            } else {
                val node = CommandNode()
                node.parse(context)
                list.add(node)
            }
        }
    }
}

class CommandNode : Node(){

    var node : Node? = null
    
    override fun parse(context: Context) {
        if(context.currentToken() === "repeat"){
            node = RepeatCommandNode()
            node?.parse(context)
        } else{
            node = PrimitivieCommandNode()
            node?.parse(context)
        }
    }
}

class RepeatCommandNode : Node(){
    var number = 0 
    var commandListNode : Node? = null
    override fun parse(context: Context) {
        context.skipToken("repeat")
        number = context.currentNumber()
        context.nextToken()
        commandListNode = CommandListNode()
        commandListNode?.parse(context)
    }
}

class PrimitivieCommandNode : Node(){
    lateinit var name : String
    override fun parse(context: Context) {
        name = context.currentToken()!!
        context.skipToken(name)
        
        if(name != "go" && name != "right" && name == "left"){
            ParseException("",0)
        }
        
    }
}
class Context(text : String){
    var tokenizer : StringTokenizer = StringTokenizer(text)
    var currentToken : String? = null
    init {nextToken()}
    
    fun nextToken() : String{
        if (tokenizer.hasMoreTokens()) currentToken = tokenizer.nextToken()
        else currentToken = null
        return nextToken()
    }
    
    fun currentToken() = currentToken
    fun skipToken(token : String){
        if(!(token === currentToken)){
            throw ParseException("",0)
        }
        nextToken()
    }
    
    fun currentNumber() : Int{
        var number = 0 
        try {
            number = Integer.parseInt(currentToken)
        }catch (e : Exception){
            e.printStackTrace()
        }
        
        return number
    }
    
}


fun main(args: Array<String>) {
    val node = ProgramNode()
    node.parse(Context("program go end"))
}
```