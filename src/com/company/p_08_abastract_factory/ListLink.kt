package com.company.p_08_abastract_factory

// 구체적인 제품
class ListLink(caption : String, url : String) : Link(caption,url){
    override fun makeHtml(): String {
        return "abc"
    }
}