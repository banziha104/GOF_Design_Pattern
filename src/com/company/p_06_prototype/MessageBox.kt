package com.company.p_06_prototype

import kotlin.properties.Delegates

class MessageBox(val decoChar : Char) : Product{


    override fun use(s: String) {
        val length = s.toByteArray().size
        for( i in 0..length+4) println(decoChar)
        println("")
        println("$decoChar $s $decoChar")
        for( i in 0..length+4) println(decoChar)
        println("")
    }

    override fun createClone(): Product {
        var p : Product? = null
        try {
            p = clone() as Product
        }catch (e : Exception){
            e.printStackTrace()
        }
        return p!!
    }
}