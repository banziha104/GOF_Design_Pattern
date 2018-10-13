package com.company.p_07_builder

class Director(val builder: Builder){ // Builder의 하위타입을 받아옮
    fun construct(){
        builder.makeString("string")
        builder.makeTitle("title")
        builder.close()
    }
}