package com.company.p_07_builder

fun main(args: Array<String>) {
    val textBuilder = TextBuilder()
    val director = Director(textBuilder)
    director.construct() // 조립실행
    val result = textBuilder.getResult()
}