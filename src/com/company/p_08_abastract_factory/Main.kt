package com.company.p_08_abastract_factory

fun main(args: Array<String>) {

    // 실행을 위해선 java Main listfactory.ListFactory 를 이용해 실행
    val factory = Factory.getFactory(args[0])
    // 구체적인 공장 없이 사용
    val link = factory?.createLink("naver","www.naver.com")
    println(link?.url)
}