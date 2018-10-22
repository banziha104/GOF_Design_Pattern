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
abstract class Support(val name: String) {
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
        println("${trouble.num} / $name 해결")
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

