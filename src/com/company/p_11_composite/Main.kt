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