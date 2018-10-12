package com.company.p_06_prototype

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