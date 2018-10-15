package com.company.p_08_abastract_factory

// 구체적인 공장
public class ListFactory : Factory(){
    override fun createLink(caption: String, url: String) : Link {
        return ListLink(caption,url)
    }
}