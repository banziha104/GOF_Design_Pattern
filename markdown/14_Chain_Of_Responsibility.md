# Chain of responsibility

> 책임 떠넘기기, 복수의 오브젝트를 사슬처럼 연결해두고, 

<br>

- ### 필요 클래스

| 이름            | 해설                                                                                                    | 대응 클래스  |
|-----------------|---------------------------------------------------------------------------------------------------------|--------------|
| Handler         | 요구를 처리하는 인터페이스를 결정하는 역할, 다음 사람을 준비해두고, 할 수없는 경우 다음 사람에게 떠넘김 | Support      |
| ConcreteHandler | 구체적인 처리자 역할                                                                                    | NoSupport 등 |
| CLient          | Concrete 역할에 요구하는 일을 함                                                                        | Main         |

<br>

- ### 예제 클래스

| 이름           | 해설                                                               |
|----------------|--------------------------------------------------------------------|
| Trouble        | 발생한 트러블을 나타내는 클래스, 트러블 번호를 가짐                |
| Support        | 트러블을 해결하는 추상 클래스                                      |
| NoSupport      | 항상 처리하지 않음                                                 |
| LimitSupport   | 지정한 번호 미만의 트러블을 해결                                   |
| OddSuport      | 홀수 번호의 트러블을 해결                                          |
| SpecialSupport | 특정 번호의 트러블을 해결                                          |
| Main           | Support들의 사슬을 만들고 트러블을 발생시키는 동작 테스트용 클래스 |


<br>

- ### 전체소스

```kotlin
package com.company.p_14_chain_of_responsibility

fun main(args: Array<String>) {
    val no = NoSupport("no")
    val limit = LimitSupport("limit",100)
    val special = SpecialSupport("special",429)
    val odd = OddSupport("odd")

    no.setNext(limit).setNext(special).setNext(odd)

    for (i in 0 .. 500) no.support(Trouble(i))
}

// 트러블을 표현
class Trouble(val num: Int)

// 지원을 표현한 클래스, 채인을 만들기 위해 필요
abstract class Support(name: String) {
    lateinit var next: Support // *중요* 떠넘기는 곳

    fun setNext(next: Support): Support { // 떠넘기는 곳을 설정
        this.next = next
        return next
    }

    fun support(trouble: Trouble) {
        if (resolve(trouble)) {
            done(trouble)
        } else if (next != null) {
            next.support(trouble)
        } else {
            fail(trouble)
        }
    }

    fun done(trouble: Trouble) {
        print("해결")
    }

    fun fail(trouble: Trouble) {
        print("해결 실패")
    }


    abstract fun resolve(trouble: Trouble): Boolean
}

class NoSupport(name: String) : Support(name) { // 아무것도 하지 않음
    override fun resolve(trouble: Trouble): Boolean = false
}

// 제한적인 역할만 가능
class LimitSupport(name: String, val limit: Int) : Support(name) {
    override fun resolve(trouble: Trouble): Boolean = trouble.num < limit
}

// 홀수만 가능
class OddSupport(name : String) : Support(name){
    override fun resolve(trouble: Trouble): Boolean = trouble.num % 2 == 1
}

// 지정한 번호의 트러블에 한하여 처리
class SpecialSupport(name : String, val num: Int) : Support(name){
    override fun resolve(trouble: Trouble): Boolean = trouble.num == num
}


```
<br>
