package com.company.p_11_composite

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