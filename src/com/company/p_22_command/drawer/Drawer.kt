package com.company.p_22_command.drawer

import com.company.p_22_command.command.Command
import com.company.p_22_command.command.MacroCommand
import java.awt.Canvas
import java.awt.Color
import java.awt.Graphics
import java.awt.Point
import java.awt.event.*
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JFrame

/***
 * Command 인터페이스를 구현할 클래스이고 점 그리기 명령을 표현한 것
 */
class DrawCommand(val drawable: Drawable,
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

class Main(title : String) : JFrame(title), ActionListener,MouseMotionListener,WindowListener{

    val history = MacroCommand()
    val canvas = DrawCanvas(400,400,history)
    val clearButton = JButton("clear")
    init {
        this.addWindowListener(this)
        canvas.addMouseMotionListener(this)
        clearButton.addActionListener(this)

        val buttonBox = Box(BoxLayout.X_AXIS)
        buttonBox.add(clearButton)
        val mainBox = Box(BoxLayout.Y_AXIS)
        mainBox.add(buttonBox)
        contentPane.add(mainBox)

        pack()
        show()
    }

    override fun actionPerformed(e: ActionEvent?) {
        if(e?.source == clearButton) {
            history.clear()
            canvas.repaint()
        }
    }

    override fun mouseMoved(p0: MouseEvent?) {
    }

    override fun mouseDragged(e: MouseEvent?) {
        // 커맨드를 불러옮
        val cmd : Command = DrawCommand(canvas,e!!.point)
        history.append(cmd)
        cmd.execute()
    }

    override fun windowDeiconified(p0: WindowEvent?) {
        System.exit(0)
    }

    override fun windowClosing(p0: WindowEvent?) {
    }

    override fun windowClosed(p0: WindowEvent?) {
    }

    override fun windowActivated(p0: WindowEvent?) {
    }

    override fun windowDeactivated(p0: WindowEvent?) {
    }

    override fun windowOpened(p0: WindowEvent?) {
    }

    override fun windowIconified(p0: WindowEvent?) {
    }
}