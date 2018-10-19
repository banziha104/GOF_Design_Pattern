package com.company.p_11_composite

class File(val str : String, var num : Int) : Entry(){
    override fun getName(): String {
        return str
    }

    override fun getSize(): Int {
        return num
    }

    override fun printList(prefix: String) {
        println("$prefix / $this ")
    }
}