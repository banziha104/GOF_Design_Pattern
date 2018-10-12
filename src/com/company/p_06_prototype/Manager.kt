package com.company.p_06_prototype

import java.util.HashMap

class Manager{

    private val showCase : HashMap<String,Product> = HashMap() // 인스턴스의 이름과 인스턴스의 대응관계를 위해 HashMap 에 다음

    fun register(name : String, proto : Product) = showCase.put(name,proto) // 해쉬 맵에 등록

    fun create(name : String) : Product?{ // Product라는 추상 타입에 의존하여 의존성 약화
        val p = showCase.get(name)
        return p?.createClone()
    }
}