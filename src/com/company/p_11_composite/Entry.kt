package com.company.p_11_composite

abstract class Entry(){

    abstract fun getName() : String
    abstract fun getSize() : Int

    fun add(entry: Entry) {
        throw FileTreatmentException()
    }

    fun printList() {
        printList("") // 일람을 표시
    }

    abstract fun printList(prefix : String)

    override fun toString(): String {
        return "${getName()} / ${getSize()}"
    }
}