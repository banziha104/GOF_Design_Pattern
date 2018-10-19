# Composite 패턴

> 그릇과 내용물을 동이시해서 `재귀적인 구조` 를 만들어냄

<br>

- ### 필요 클래스

| 이름      | 해설                                                               | 대응클래스 |
|-----------|--------------------------------------------------------------------|------------|
| Leaf      | 내용물을 표시하는 역할, 다른 것을 넣을 순 없음                     | File       |
| Composite | 그릇을 나타내는 역할로 Leaf 역할이나 Composite 역할을 넣을 수 있음 | Directory  |
| Component | Leaf와 Composite역할을 동일시하는 역할을 함                        | Entry      |
| Clienet   | 패턴의 사용자                                                      | Main       |

<br>

- ### 예제 클래스 

| 이름                   | 해설                                                 | 대응 클래스 |
|------------------------|------------------------------------------------------|-------------|
| Entry                  | File과 Directory를 동일시하는 추상 클래스            | Component            |
| File                   | 파일을 나타내는 클래스                               | Leaf            |
| Directory              | 디렉터리를 나타내는 클래스                           | Composite            |
| FireTreatmentException | 파일에 Entry를 추가하려고 할 때 발생하는 예외 클래스 |             |
| Main                   | 동작테스트용                                         | Client            |

<br>

- Entry : 컴포넌트 역할로, 동일시 하는 역할을 수행


```kotlin
abstract class Entry{

    abstract fun getName() : String
    abstract fun getSize() : Int
    
    constructor(entry : Entry) : super() { // 엔트리 추가
        throw FileTreatmentExeption()
    }
    
    fun printList() {
        printList("") // 일람을 표시
    }
    
    abstract fun printList(prefix : String)

    override fun toString(): String {
        return "${getName()} / ${getSize()}"
    }
}
```

<br>

- File 클래스

```kotlin

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
```

<br>

- Directory 

```kotlin

class Directory(private var str: String) : Entry() {
    val directory = arrayListOf<Directory>()

    fun addEntry(entry: Entry) : Entry{
        directory.add(entry as Directory)
        return this
    }
    override fun getName(): String {
        return str
    }

    override fun getSize(): Int = directory.size

    override fun printList(prefix: String) {
        println("$prefix / $this ")
    }
}
``` 

<br>

- Main

```kotlin
package com.company.p_11_composite

fun main(args: Array<String>) {
    try {
        var rootdir = Directory("root")
        var bindir = Directory("bin")
        rootdir.addEntry(bindir)
        
        bindir.add(File("txt.txt",10))
        
    } catch (e : FileTreatmentException){
        e.printStackTrace()
    }
}
```
