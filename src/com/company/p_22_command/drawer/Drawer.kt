package com.company.p_22_command.drawer

import com.company.p_22_command.command.Command
import com.company.p_22_command.command.MacroCommand
import java.awt.Canvas
import java.awt.Color
import java.awt.Graphics
import java.awt.Point

/***
 * Command 인터페이스를 구현할 클래스이고 점 그리기 명령을 표현한 것
 */
class DrawerCommand(val drawable: Drawable,
                    val position: Point) : Command {

    override fun execute() {
        drawable.draw(position.x, position.y)
    }
}

interface Drawable {
    fun draw(x: Int, y: Int)
}

/***
 *
 */
class DrawCanvas(val pWidth: Int,
                 val pHeight: Int,
                 val history: MacroCommand) : Canvas(), Drawable {
    private val color = Color.red
    private val radius = 6

    init {
        setSize(pWidth, pHeight)
        background = Color.white
    }

    override fun print(g: Graphics) {
        history.execute()
    }

    override fun draw(x: Int, y: Int) {
        val g = graphics
        g.color = color
        g.fillOval(x - radius,
                y - radius,
                radius * 2,
                radius * 2)
    }
}

fun main(args: Array<String>) {

}