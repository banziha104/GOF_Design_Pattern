package com.company.p_07_builder

import java.nio.Buffer

// 빌더를 실제로 구현한 클래스
class TextBuilder : Builder(){
    private val buffer = StringBuffer()
    override fun makeTitle(title: String) {
        buffer.append("======")
        buffer.append(title)
    }

    override fun makeString(stirng: String) {
        buffer.append(String())
    }

    override fun close() = println("-----")

    fun getResult() = buffer.toString()
}