package com.company.p_09_bridge

fun main(args: Array<String>) {
    val d1 = Display(StringDisplayImpl("Hello, World"))
    val d2 = CountDisplay(StringDisplayImpl("Hello, Hell!!!!!!"))
    val d3 = CountDisplay(StringDisplayImpl("Hello, haven!!!!!!"))

    d1.display()
    d2.display()
    d3.display()

    d3.multiDisplay(3)
}