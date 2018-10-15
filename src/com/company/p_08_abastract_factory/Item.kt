package com.company.p_08_abastract_factory

// 추상적인 부품
abstract class Item(val caption : String){
    abstract fun makeHtml() : String
}