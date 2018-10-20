package com.company.p_13_visitor

import java.lang.RuntimeException



/* 방문자를 받아들이는 인터페이스 */
interface Element{
    fun accept(v : Visitor)
}


/**
 * Directory와 File을 동일시함과 동시에
 * Visitor의 방문을 받아드릴 수 있도록 하는클래스
 * accept는 하위 클래스에서 처리*/
abstract class Entry : Element {
    abstract fun getName() : String
    abstract fun getSize() : Int
    fun add(entry : Entry){
        throw FileTreatmentException()
    }

    fun iterator() : Iterator<Entry>{
        throw FileTreatmentException()
    }

    override fun toString(): String = "${getName()} ( ${getSize()} )"
}


// file 을 나타냄, Visitor를 받는 accept를 구현
class File(val name2 : String
           , val size2 : Int) : Entry(){
    override fun accept(v: Visitor) {
        v.visit(this)
    }

    override fun getName(): String = name2

    override fun getSize(): Int = size2
}


/**
 * */
class Directory(val name2 : String) : Entry(){

    val dir = arrayListOf<Entry>()

    override fun accept(v: Visitor) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getName(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSize(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


class Visitor





class FileTreatmentException() : RuntimeException()