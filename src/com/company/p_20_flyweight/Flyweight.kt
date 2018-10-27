package com.company.p_20_flyweight

import java.io.BufferedReader
import java.io.FileReader
import kotlin.coroutines.experimental.buildIterator

class BigChar(val char : Char){
    init {
        try {
            val reader = BufferedReader(FileReader("big$char.txt"))
            val buf = StringBuffer()
            var line = ""
            reader.useLines {
                buf.append(line)
                buf.append("\n")
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

    fun print() = print(char)
}

/***
 * 핵심적인 클래스로, 기존에 만들어진 인스턴스를 관리한다
 */
object BigCharFactory{
    // 이미 만들어진 BigChar의 인스턴스를 관리
    private val pool = HashMap<String,BigChar>()

    /***
     * 만약 기존 객체가 존재하면 해당 객체를 해쉬맵에서 가져옮
     * 만약 없다면 해쉬맵에 넣음
     * 이를 통해 공유하여 자원낭비를 줄임
     */
    @Synchronized
    fun getBigChar(char : Char) : BigChar{
        var bc = pool["$char"]
        if(bc == null){
            bc = BigChar(char)
            pool[""+char] = bc
        }
        return bc
    }
}

class BigString(var string: String){
    private var bigchar = arrayOf<BigChar>()
    init {
        for ( i in 0..bigchar.size) BigCharFactory.getBigChar(string.first())
    }

    fun print(){
        for(i in bigchar) i.print()
    }
}

fun main(args: Array<String>) {
    val bs = BigString(args[0])
    bs.print()
}