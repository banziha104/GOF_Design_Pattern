package com.company.p_22_command.command

import java.util.*

// 명령을 표현하기 위한 인터페이스
interface Command{
    fun execute()
}

/***
 * 복수의 명령을 모은 명령
 */
class MacroCommand : Command{
    private val commands = Stack<Command>()

    override fun execute() {
        val it = commands.iterator()
        while (it.hasNext()){
            it.next().execute()
        }
    }

    fun append(cmd : Command) = if (cmd != this){commands.push(cmd)} else {}
    fun undo() = if (!commands.empty()) commands.pop() else {}
    fun clear() = commands.clear()
}
