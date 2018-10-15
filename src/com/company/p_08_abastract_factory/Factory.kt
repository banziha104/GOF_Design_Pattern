package com.company.p_08_abastract_factory
// 추상적인 공장
abstract class Factory{
    companion object {
        fun getFactory(className : String) : Factory?{
            var factory : Factory? = null
            try {
                // 클래스를 읽어냄
                factory = Class.forName(className).newInstance() as Factory
            } catch (e : Exception){
                e.printStackTrace()
            }
            return factory
        }
    }

    // Link 생성 추상화
    abstract fun createLink(caption : String, url : String) : Link
}