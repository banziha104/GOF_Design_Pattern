package com.company.p_06_prototype

interface Product : Cloneable{ // 자바에서 제공하는 인터페이스며, 복제를 가능하게함
    fun use(s : String)
    fun createClone() : Product // 자체적인 복사를 허용
}