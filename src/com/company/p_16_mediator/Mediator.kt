package com.company.p_16_mediator

import java.awt.*
import java.awt.event.*


// 중개인 인터페이스
interface Mediator{
    fun createColleagues() // 각 회원
    fun changeColleagues() // 회원 변경
}

// 추상화 된 인터페이스
interface Colleague {
    fun setMediator(mediator : Mediator) //중개인 지정
    fun setColleagueEnabled(enabled : Boolean)
}


// Mediator와 협동하여 처리
class ColleagueButton(caption : String) : Button(caption), Colleague{
    private lateinit var mediator : Mediator

    override fun setMediator(mediator: Mediator) { // 중개인 지정
        this.mediator = mediator
    }

    override fun setColleagueEnabled(enabled: Boolean) {
        isEnabled = enabled
    }
}

class ColleagueTextField(text : String, columes : Int) : TextField(text, columes), TextListener, Colleague{
    private lateinit var mediator: Mediator

    override fun textValueChanged(p0: TextEvent?) { // 문자열이 변하면 Mediator에게 통지
        mediator.changeColleagues()
    }

    override fun setMediator(mediator: Mediator) {
        this.mediator = mediator
    }

    override fun setColleagueEnabled(enabled: Boolean) {
        isEnabled = enabled
    }
}

class ColleagueCheckbox(caption : String, group : CheckboxGroup, state : Boolean) : Checkbox(caption,group,state), ItemListener, Colleague{
    private lateinit var mediator: Mediator

    override fun itemStateChanged(p0: ItemEvent?) {
        mediator.changeColleagues()
    }

    override fun setMediator(mediator: Mediator) {
        this.mediator = mediator
    }

    override fun setColleagueEnabled(enabled: Boolean) {
        isEnabled = enabled
    }
}

class LoginFrame(title : String) : Frame(title), ActionListener, Mediator{
    private lateinit var checkGuest : ColleagueCheckbox
    private lateinit var checkLogin : ColleagueCheckbox
    private lateinit var textUser : ColleagueTextField
    private lateinit var textPass : ColleagueTextField
    private lateinit var buttonOk : ColleagueButton
    private lateinit var buttonCancel : ColleagueButton

    init {
        background = Color.lightGray
        layout = GridLayout(4,2)
        createColleagues()
        add(checkGuest)
        add(checkLogin)
        add(Label("UserName:"))
        add(textUser)
        add(Label("PassWord:"))
        add(textPass)
        add(buttonOk)
        add(buttonCancel)
        changeColleagues()
        pack()
        show()
    }


    override fun createColleagues() {
        val g = CheckboxGroup()
        checkGuest = ColleagueCheckbox("Guest",g,true)
        checkLogin = ColleagueCheckbox("Login",g,false)
        textUser = ColleagueTextField("",10)
        textPass = ColleagueTextField("",10)
        textPass.echoChar = '*'
        buttonOk = ColleagueButton("Ok")
        buttonCancel = ColleagueButton("Cancel")

        // Mediator 지정
        checkGuest.setMediator(this)
        checkLogin.setMediator(this)
        textUser.setMediator(this)
        textPass.setMediator(this)
        buttonOk.setMediator(this)
        buttonCancel.setMediator(this)

        checkGuest.addItemListener(checkGuest)
        checkLogin.addItemListener(checkLogin)
        textUser.addTextListener(textUser)
        textPass.addTextListener(textPass)
        buttonOk.addActionListener(this)
        buttonCancel.addActionListener(this)

    }

    override fun changeColleagues() { // 유무효 판정을함, Colleage가 아닌 Mediator에서 처리함으로 가장 중요한 부분
        if(textUser.text.isNotEmpty()){
            textUser.setColleagueEnabled(false)
            textPass.setColleagueEnabled(false)
            buttonOk.setColleagueEnabled(true)
        }else{
            textUser.setColleagueEnabled(true)
            userpassChanged()
        }
    }

    private fun userpassChanged(){
        if(textUser.text.isNotEmpty()){
            textPass.setColleagueEnabled(true)
            if(textPass.text.isNotEmpty()) buttonOk.setColleagueEnabled(true)
            else buttonOk.setColleagueEnabled(false)
        }else{
            textPass.setColleagueEnabled(false)
            buttonOk.setColleagueEnabled(false)
        }
    }

    override fun actionPerformed(e: ActionEvent?) {
        println(e.toString())
        System.exit(0)
    }
}

fun main(args: Array<String>) {
    LoginFrame("Mediator Sample")
}
