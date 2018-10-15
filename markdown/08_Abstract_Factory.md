# 추상 팩토리

> 관련 부품을 조합해서 제품을 만든다 

<br>

- ### 필요 클래스

| 이름            | 해설                                                                                          | 대응 클래스 |
|-----------------|-----------------------------------------------------------------------------------------------|-------------|
| AbstractProduct | 추상적 제품                                                                                   | Link        |
| AbstractFactory | 추상적인 공장의 역할로, AbstractProduct 역할의 인스턴스를 만들어내기 위한 인터페이스를 결정함 | Factory     |
| Client          | AbstractFactory 역할과 AbstractProduct 역할의 인터페이스를 이용함                             | Main        |
| ConcreteProduct | 구체적인제품                                                                                  | ListLink    |
| ConcreteFactory | 구체적인 공장역할                                                                             | ListFactory |

<br>

- ### 예제 클래스

| 이름        | 해설                                          | 대응 클래스 |
|-------------|-----------------------------------------------|-------------|
| Factory     | 추상적인 공장을 나타내는 클래스               | ㄴ          |
| Item        | Link와 Tray를 통일적으로 취급하기 위한 클래스 | ㄴ          |
| Link        | 추상적인 부품                                 |             |
| Tary        | 추상적인 부품                                 |             |
| ListFactory | 구체적인 공장                                 |             |
| ListLink    | 구체적인 부품                                 |             |
| ListTray    | 구체적인 부품                                 |             |
| Main        | 실행                                          |             | 


<br>

- Item

```kotlin
// 추상적인 부품
abstract class Item(val caption : String){
    abstract fun makeHtml() : String
}
```

<br>

- Link : 추상적인 부품 정의 

```kotlin

// 추상메소드가 구현되지 않았으므로 Link는 추상타입
abstract class Link(caption : String, val url : String) : Item(caption)

```

<br>

- Factory : 추상적인 공장 

```kotlin
// 추상적인 공장
abstract class Factory{
    companion object {
        fun getFactory(className : String) : Factory?{
            var factory : Factory? = null
            try {
                // 클래스를 읽어냄
                factory = Class.forName(className).newInstance() as Factory
            } catch (e : Exception){
                e.printStackTrace()
            }
            return factory
        }
    }

    // Link 생성 추상화
    abstract fun createLink(caption : String, url : String) : Link
}
```

<br>

- Main 

```kotlin

fun main(args: Array<String>) {

    // 실행을 위해선 java Main listfactory.ListFactory 를 이용해 실행
    val factory = Factory.getFactory(args[0])
    // 구체적인 공장 없이 사용
    val link = factory?.createLink("naver","www.naver.com")
    println(link?.url)
}
```


<br>

- ListFactory : 구체적인 공장

```kotlin

// 구체적인 공장
public class ListFactory : Factory(){
    override fun createLink(caption: String, url: String) : Link {
        return ListLink(caption,url)
    }
}
```

- ListLink : 구체적인 제품

```kotlin
package com.company.p_08_abastract_factory

// 구체적인 제품
class ListLink(caption : String, url : String) : Link(caption,url){
    override fun makeHtml(): String {
        return "abc"
    }
}
```