# Prototype 

> 복사해서 인스턴스 만들기 

- 종류가 너무 많아 클래스로 정리되지 않아 정리가 되지 않는 경우
- 클래스로부터 인스턴스 생성이 어려운 경우
- framework와 생성할 인스턴스 생성이 어려운 경우

<br>

- ### 필요 클래스 

| 이름              | 해설                                                                           | 대응 예제 클래스         |
|-------------------|--------------------------------------------------------------------------------|--------------------------|
| Prototype         | 원형의 역할로, 인스턴스를 복사하여 새로운 인스턴스를 만들기 위한 메소드를 결정 | Product                  |
| ConcretePrototype | 인스턴스를 복사해서 새로운 인스턴스를 만드는 메소드를 실제로 구현              | MessageBox, UnderlineBox |
| Cline             | 인스턴스를 복사하는 메소드를 이용해서 새로운 인스턴스를 만듬                   | Manager                  |

<br>

- ### 예제 클래스

| 이름         | 해설                                              | 대응 클래스 |
|--------------|---------------------------------------------------|-------------|
| Product      | 추상 메소드 use와 createClone이 선언된 인터페이스 | Prototype          |
| Manager      | createClone을 사용해서 인스턴스를 복제하는 클래스 | Client          |
| MessageBox   | 문자열을 테두리로 표시하는 클래스                 |  ConcretePrototype          |
| UnderlinePen | 문자열에 밑줄을 표시하는 클래스                   | ConcretePrototype          |
| Main         | 실행 담당                                         | Main          |

<br>

- Product : Cloneable을 받아 복제가 가능한 인터페이스로 만듬

```kotlin
package com.company.p_06_prototype

interface Product : Cloneable{ // 자바에서 제공하는 인터페이스며, 복제를 가능하게함
    fun use(s : String)
    fun createClone() : Product // 자체적인 복사를 허용
}
```

- Manager : 인스턴스의 복제를 실행하는 클래스

```kotlin

class Manager{
    
    private val showCase : HashMap<String,Product> = HashMap() // 인스턴스의 이름과 인스턴스의 대응관계를 위해 HashMap 에 다음
    
    fun register(name : String, proto : Product) = showCase.put(name,proto) // 해쉬 맵에 등록
    fun create(name : String) : Product?{ // Product라는 추상 타입에 의존하여 의존성 약화
        val p = showCase.get(name)
        return p?.createClone()
    }
}
``` 

- MessageBox, Underline : Product를 구현하고, 인스턴스의 복제를 실행하는 클래스

```kotlin



class MessageBox(val decoChar : Char) : Product{


    override fun use(s: String) {
        val length = s.toByteArray().size
        for( i in 0..length+4) println(decoChar)
        println("")
        println("$decoChar $s $decoChar")
        for( i in 0..length+4) println(decoChar)
        println("")
    }

    override fun createClone(): Product {
        var p : Product? = null
        try {
            p = clone() as Product
        }catch (e : Exception){
            e.printStackTrace()
        }
        return p!!
    }
}


class UnderlinePen(val urChar : Char) : Product{
    override fun use(s: String) {
        val length = s.toByteArray().size
        println("/ $s /")
        for( i in 0..length) println(urChar)
        println()
    }

    override fun createClone(): Product {
        var p : Product? = null
        try {
            p = clone() as Product // 자기자신을 복제하는 자바 기본함수인 clone 사용, Cloneable 을 구현해야만 사용가능
        }catch (e : Exception){
            e.printStackTrace()
        }
        return p!!
    }
}

```


- Main : 실행

```kotlin
package com.company.p_06_prototype

fun main(args: Array<String>) {
    val manager = Manager()
    val upen = UnderlinePen('_')
    val mbox = MessageBox('*')
    
    // 메니저에 등록
    manager.register("upen",upen)
    manager.register("mbox",mbox)
    
    // 생성
    val p1 : Product? = manager.create("upen")
    val p2 : Product? = manager.create("mbox")
    
    p1?.use("abc")
    p2?.use("cde")
    
}
```
