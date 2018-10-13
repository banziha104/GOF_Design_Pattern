package com.company.p_07_builder

// 문서를 만들 메소드들을 선언하고 있는 추상클래스
abstract class Builder{
    abstract fun makeTitle(title : String)
    abstract fun makeString(stirng : String)
    abstract fun close()
}