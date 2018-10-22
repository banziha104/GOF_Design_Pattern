package com.company.p_13_visitor

import java.lang.RuntimeException


fun main(args: Array<String>) {
    try {
        var rootdir = Directory("root")
        var bindir = Directory("bin")

        bindir.add(File("txt.txt", 10))
        rootdir.accept(ListVisitor())
    } catch (e: FileTreatmentException) {
        e.printStackTrace()
    }
}

/* 방문자를 받아들이는 인터페이스 */
interface Element {
    fun accept(v: Visitor)
}


/**
 * Directory와 File을 동일시함과 동시에
 * Visitor의 방문을 받아드릴 수 있도록 하는클래스
 * accept는 하위 클래스에서 처리*/
abstract class Entry : Element {
    abstract fun getName(): String
    abstract fun getSize(): Int

    open fun add(entry: Entry): Entry {
        throw FileTreatmentException()
    }

    fun iterator(): Iterator<Entry> {
        throw FileTreatmentException()
    }

    override fun toString(): String = "${getName()} ( ${getSize()} )"
}


// file 을 나타냄, Visitor를 받는 accept를 구현
class File(val name2: String
           , val size2: Int) : Entry() {
    override fun accept(v: Visitor) {
        v.visit(this)
    }

    override fun getName(): String = name2

    override fun getSize(): Int = size2
}


/**
 * */
class Directory(val name2: String) : Entry() {

    val dir = arrayListOf<Entry>()

    override fun add(entry: Entry): Entry {
        dir.add(entry)
        return this
    }

    override fun accept(v: Visitor) {
        v.visit(this) // 방문
    }

    override fun getName(): String {
        return name2
    }

    override fun getSize(): Int {
        var size = 0
        val it = dir.iterator()
        while (it.hasNext()) {
            val entry = it.next()
            size += entry.getSize()
        }
        return size
    }
}


class ListVisitor : Visitor() {
    var currentDir = ""
    override fun visit(file: File) {
        println(file)
    }

    override fun visit(directory: Directory) {
        println("$currentDir / ${directory.getName()}")
        var savedir = currentDir
        currentDir = "$currentDir / ${directory.getName()}"
        val it = directory.iterator()
        while (it.hasNext()) {
            val entry = it.next()
            entry.accept(this)
        }
        currentDir = savedir
    }
}

abstract class Visitor {
    abstract fun visit(file: File)
    abstract fun visit(directory: Directory)
}


class FileTreatmentException() : RuntimeException()