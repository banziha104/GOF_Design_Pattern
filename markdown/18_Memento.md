# Memento 

> 상태를 저장하기

<br>

- ctrl + z 처럼 되돌아가기를 위해서는 상태 저장이 필수
- 저장만으로는 부족하고 저장한 정보로부터 인스터느를 원래 상태로 돌려놓아야함
- 인스턴스를 복원하기 위해서는 인스턴스 내부의 정보를 자유롭게 액세스 할수 있어야함
- 원치않는 액세스를 허용할 경우 `캡슐화의 파괴` 가 일어남
- Memento는 인스턴스의 상태를 나타내는 역할을 도입해서 캡슐화의 파괴에 빠지지않고 저장과 복원을 같이함

<br>

- ### 필요클래스

| 이름       | 해설                                                                                                                                            | 대응클래스 |
|------------|-------------------------------------------------------------------------------------------------------------------------------------------------|------------|
| Originator | 현재 상태를 저장하고 싶을 때 Memento 역할을 만듬, Originator 역할은  이전의 Memento를 전달받으면 그 Memnto 역할을 만든시점으로 돌리는 역할을함  | Gamer      |
| Memnto     | Originator 역할의 내부 정보를 정리함                                                                                                            | Memento    |
| Caretaker  | Originator 역할의 상태를 저장하고 싶을 때 , 저장하고자 하는 역할을 Originator에게 전달                                                          | Main       |

<br> 

- ### 예제클래스

| 패키지    | 이름    | 해설                                                                                      |
|-----------|---------|-------------------------------------------------------------------------------------------|
| game      | Memento | Gamer의 상태를 나타내는 클래스                                                            |
| game      | Gamer   | 게임을 실행하는 주인공 클래스, Memento의 인스턴스를 만든다                                |
| Anonymous | Main    | 게임을 진행시키는 클래스, Memento의 인스턴스를 저장해두고 필요에 따라서 Gamer 상태를 복원 |

<br>

- ### 전체소스

```kotlin

// Game 패키지의 Memento
// internal 로 game 패키지 외부에서 Memento를 변경할수 없도록함
class Memento(var meony : Int){
    internal var fruits = arrayListOf<String>()
    get() = field.clone() as ArrayList<String>

    internal fun addFruit(fruit : String) = fruits.add(fruit)
}

// =============================================================================================
// Game 패키지의 Gamer
import java.util.*

class Gamer(
        var meony : Int){
    private var fruits = arrayListOf<String>()
    private val random = Random()
    private val frutisname = arrayOf("사과","포도","바나나","귤")
    fun bet(){
        var dice = random.nextInt(6) + 1
        when(dice){
            1 -> {
                meony += 100
                println("소지금이 증가함")
            }
            2 -> {
                meony /= 2
                println("소지금 절반")
            }
            6 -> {
                val f = getFruit()
                println("과일 ${f}를 받았음")
                fruits.add(f)
            }
            else ->{
                println("변한것이 없음")
            }
        }
    }

    /***
     * 핵심적인 부분
     * 스냅샷을 찍어둠
     */
    fun createMemento() : Memento{
        var m = Memento(meony)
        val it = fruits.iterator()
        while (it.hasNext()) {
            val f = it.next()
            if(f.startsWith("맛있는")) m.addFruit(f)
        }
        return m
    }

    /***
     * 뒤로가기 실행
     * 일종의 부활주문
     */
    fun restoreMemento(memento: Memento){
        this.meony = memento.meony
        this.fruits = memento.fruits
    }

    override fun toString(): String {
        return "[meney + $meony ,fruits = $fruits]"
    }

    private fun getFruit() : String{
        var prefix = ""
        if(random.nextBoolean()) prefix = "맛있는"
        return prefix + frutisname[random.nextInt(frutisname.size)]
    }
}

// =============================================================================================
// Main
fun main(args: Array<String>) {
    val gamer = Gamer(100)
    var memento = gamer.createMemento() // 최초 상태를 저장함

    for(i in 0..100){
        println("===== $i")
        println("주인공의 상태 : $gamer")

        gamer.bet()

        println("소지금은 ${gamer.meony} 원이 되었습니다")
        if(gamer.meony > memento.meony){
            println("증가했음으로 현재 상태를 저장")
            memento = gamer.createMemento() // 현재 상태를 저장함
        }else if(gamer.meony < memento.meony / 2){
            println("많이 감소했음으로 이전 상태로 복원")
            gamer.restoreMemento(memento)
        }

        try{
            Thread.sleep(1000)
        }catch (e : Exception){
            e.printStackTrace()
        }

        println()
    }
}

```
