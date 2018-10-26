# State

> 상태를 클래스로 표현하기

<br>

- ### 필요클래스

| 이름          | 해설                                                                                                  | 대응 클래스          |
|---------------|-------------------------------------------------------------------------------------------------------|----------------------|
| State         | 상태가 변할 때마다 다른 동작을 하는 인터페이스를 결정                                                 | State                |
| ConcreteState | 구체적인 각각의 상태를 표현                                                                           | DayState, NightState |
| Context       | 현재 상태를 나타내는 ConcreteState 엵할을 가지며, State 패턴의 이용자에게  필요한 인터페이스를 결정함 | SafeFrame            |

<br>

- ### 예제 클래스

| 이름       | 해설                                                                       |
|------------|----------------------------------------------------------------------------|
| State      | 금고의 상태를 나타내는 인터페이스                                          |
| DayState   | State를 구현하며, 주간의 상태를 나타냄                                     |
| NightState | State를 구현하며, 야간의 상태를 나타냄                                     |
| Context    | 금고의 상태변화를 관리하고, 경비센터와 연락을 취하는 인터페이스            |
| SafeFrame  | Context를 구현하는 클래스, 버튼이나 화면표시 등의 사용자 인터페이스를 가짐 |

<br>

- ### 전체코드

```kotlin
package com.company.p_19_state

import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.time.chrono.JapaneseDate

// 이벤트에 대응해서 호출되는 메소드들을 표현한 인터페이스
interface State{
    fun doClock(context : Context , hour : Int)
    fun doUse(context : Context)
    fun doAlarm(context : Context)
    fun doPhone(context : Context)
}

// State를 구현
class DayState : State{
    
    companion object {
        private val singleton = DayState()
        fun getInstance() = singleton
    }
    
    override fun doClock(context: Context, hour: Int) {
        if(hour < 9 || 17 <= hour) context.changeState(NightState.getInstance())
    }

    override fun doUse(context: Context) = context.recordLog("금고사용(주간):")
    

    override fun doAlarm(context: Context) = context.callSecurityCenter("비상벨(주간)")

    override fun doPhone(context: Context) = context.callSecurityCenter("일반 통화(주간)")

    override fun toString() = "[주간]"
}


class NightState : State{
    companion object {
        private val singleton = NightState()
        fun getInstance() = singleton
    }
    override fun doClock(context: Context, hour: Int){
        if(hour in 9..16) context.changeState(DayState.getInstance())
    } 

    override fun doUse(context: Context) = context.callSecurityCenter("비상 : 야간 금고 사용")
    

    override fun doAlarm(context: Context) = context.callSecurityCenter("비상벨(야간)")
    

    override fun doPhone(context: Context) = context.recordLog("야간 통화 녹음")

    override fun toString(): String = "[야간]"
}


// 상태를 관리하는 인터페이스
interface Context{
    fun setClock(hour: Int)
    fun changeState(state: State)
    fun callSecurityCenter(msg: String)
    fun recordLog(msg : String)
}

// State를 구현하여 실제 상태를 이용하는 클래스
class SafeFrame(title : String) : Frame(title), ActionListener, Context {
    
    private val textClock = TextField(60)
    private val textScreen = TextArea(10,60)
    private val buttonUse = Button("금고사용")
    private val buttonAlarm = Button("비상벨")
    private val buttonPhone = Button("일반통화")
    private val buttonExit = Button("종료")
    
    private var state : State= DayState.getInstance()
    
    init {
        background = Color.lightGray
        layout = BorderLayout()
        
        add(textClock, BorderLayout.NORTH)
        textClock.isEditable = false 
        
        add(textScreen, BorderLayout.CENTER)
        textScreen.isEditable = false
        
        val paner = Panel()
        
        paner.add(buttonUse)
        paner.add(buttonAlarm)
        paner.add(buttonPhone)
        paner.add(buttonExit)
        
        add(paner, BorderLayout.SOUTH)
        
        pack()
        show()
        
        buttonUse.addActionListener(this)
        buttonExit.addActionListener(this)
        buttonPhone.addActionListener(this)
        buttonAlarm.addActionListener(this)
    }
    override fun actionPerformed(e: ActionEvent?) {
        println(e.toString())
        when(e?.source){
            buttonUse -> state.doUse(this)
            buttonAlarm -> state.doAlarm(this)
            buttonPhone -> state.doPhone(this)
            buttonExit -> System.exit(0)
            else -> {
                println("?")
            }
        }
    }

    override fun setClock(hour: Int) {
        var clockString = "현재 시간은"
        clockString += if(hour < 10) "0$hour:00" else "$hour:00"
        println(clockString)
        textClock.text = clockString
        state.doClock(this,hour)
    }

    override fun changeState(state: State) {
        println("${this.state}에서 ${state}로 상태가 변경")
        this.state = state
    }

    override fun callSecurityCenter(msg: String) {
        textScreen.append("call! $msg \n")
    }

    override fun recordLog(msg: String) {
        textScreen.append("recoard ... $msg \n")
    }
}

fun main(args: Array<String>) {
    val frame = SafeFrame("State Sample")
    while (true){
        for(i in 0..24){
            frame.setClock(i)
            try {
                Thread.sleep(1000)
            } catch (e : Exception){
                
            }
        }
    }
}
```