# Visitor 

> 데이터 구조를 돌아다니면서 처리하기 

<br>

- ### 필요클래스

| 이름            | 해설                                                                                | 대응 클래스 |
|-----------------|-------------------------------------------------------------------------------------|-------------|
| Visitor         | Visitor는 데이터 구조의 구체적인 요소를 확인하는 visit 메소드를 선언함.             | Visitor     |
| ConcreteVisitor | Visitor 역할의 인터페이스를 구현함                                                  | ListVisitor |
| Element         | Visitor 역할이 방문할 곳을 나타내는 역할로 방문자를 받아들이는 accept 메소드를 선언 | Elemenet    |
| concreteElement | Element 역할을 구현                                                                 | Entry       |
| ObjectStructure | Element 역할의 집합을 취급하는 역할, Element 역할을 취급할수 있는 메소드를 구비함   | Directory   |


<br>

- ### 예제 클래스

| 이름                  | 해설                                                                  |
|-----------------------|-----------------------------------------------------------------------|
| Visitor               | 파일과 디렉터리를 방문하는 방문자를 나타내는 추상 클래스              |
| Elemenet              | Vistor 클래스의 생성자를 받아들이는 데이터 구조를 나타내는 인터페이스 |
| ListVisitor           | Visitor 하위 클래스로 파일과 디렉터리 종류를 나타내는 클래스          |
| Entry                 | File과 Directory 상위 클래스가 되는 추상 클래스                       |
| File                  | 파일을 나타내는 클래스                                                |
| Directory             | 디렉터리를 나타내는 클래스                                            |
| FileTretmentException | File에서 add 한 경우 발생하는 예외 클래스                             |
| Main                  | 동작테스트용 클래스                                                   |


<br>

- ### 전체 소스 

```kotlin
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
```