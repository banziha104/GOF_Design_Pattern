# Strategy 패턴

> 알고리즘을 모두 바꾸기

<br>

- ### 필요클래스

| 이름             | 해설                                   | 예제 대응 클래스              |
|------------------|----------------------------------------|-------------------------------|
| Strategy         | 전략을 이용하기 위한 인터페이스를 결정 | Strategy                      |
| ConcreteStrategy | 전략 인터페이스를 실제로 구현          | WinningStrategy, ProbStrategy |
| Context          | Strategy를 이용하는 역할               | Player                        |

<br>

- ### 대응클래스

| 이름            | 해설                                                  | 대응 클래스 |
|-----------------|-------------------------------------------------------|-------------|
| Hand            | 손을 나타내는 클래스                                  |             |
| Strategy        | 전략을 나타내는 인터페이스                            | Strategy            |
| WinningStrategy | 이기면 다음에도 같은 손을 내는 전략을 표시하는 클래스 | ConcreteStrategy            |
| ProbStrategy    | 직전 손에서 다음 손을 확률적으로 계산하는 클래스      | ConcreteStrategy            |
| Player          | 가위바위보를 하는 플레이어를 표시하는 클래스          |  Context           |
| Main            | 실행                                                  |             |

<br>

- Hand 클래스 

```kotlin

class Hand(val value : Int){
    companion object {
        val GUU = 0
        val CHO = 1
        val PAA = 2
        val hand = arrayOf(
                Hand(GUU),
                Hand(CHO),
                Hand(PAA)
        )
        val name = arrayOf(
                "주먹",
                "가위",
                "보"
        )
    }

    // 비교하는 메소드
    fun fight(h: Hand) : Int{
        if(this == h){
            return 0
        }else if ((this.value +1) % 3 == h.value ){
            return 1
        }else{
            return -1
        }
    }

    fun isStrongThan(h : Hand) : Boolean{
        return fight(h) == 1
    }
    fun isWeakerThan(h : Hand) : Boolean{
        return fight(h) == -1
    }

}

```

<br>

- Strategy : 전략을 정의하는 인터페이스 

```kotlin
package com.company.p_10_strategy

// 전략 정의
interface Strategy{
    fun nextHand() : Hand
    fun study(win: Boolean)
}
```

<br>

- WinningStrategy : 첫번쨰 전략 

```kotlin

// 이겼을 때의 전략 
class WinningStrategy(seed : Int) : Strategy{
    
    var random = Random(seed.toLong())
    var won = false
    lateinit var prevHand: Hand
            
    override fun nextHand(): Hand {
        if(!won){
            prevHand = Hand.getHand(random.nextInt(3))
        }
        return prevHand
    }

    override fun study(win: Boolean) {
        won = win
    }
}
``` 

<br> 

- ProbStrategy : 두번째 전략 

```kotlin
class ProbStrategy(seed : Int) : Strategy{
    val random = Random(seed.toLong())
    var prevHandValue = 0 
    var currentHandValue = 0 
    var history = arrayOf(
            arrayOf(1,1,1),
            arrayOf(1,1,1),
            arrayOf(1,1,1)
    )

    override fun nextHand(): Hand {
        val bet = random.nextInt(getSum(currentHandValue))
        var handvalue = 0 
        if ( bet < history[currentHandValue][0]){
            handvalue = 0
        } else if( bet < history[currentHandValue][0] + history[currentHandValue][1]){
            handvalue = 1
        }else {
            handvalue = 2
        }
        prevHandValue = currentHandValue
        currentHandValue = handvalue
        return Hand.getHand(handvalue)
    }

    override fun study(win: Boolean) {
        if(win) history[prevHandValue][currentHandValue]++
        else {
            history[prevHandValue][(currentHandValue + 1) % 3]++
            history[prevHandValue][(currentHandValue + 2) % 3]++
        }
    }
    
    fun getSum(hv : Int) : Int{
        var sum = 0
        for (i in 0..3) sum += history[hv][i]
        return sum
    }
}
``` 

<br>

- Player : 전략의 상태를 바꾸는 객체 

```kotlin
package com.company.p_10_strategy

// 전략의 내부상태를 바꿈
class Player(name : String, val strategy: Strategy){
    var wincount = 0 
    var gamecount = 0 
    var losecount =0 
    
    fun nextHand() = strategy.nextHand()
    fun win(){
        strategy.study(true)
        wincount ++
        gamecount ++
    }
    
    fun lose(){
        strategy.study(false)
        losecount++
        gamecount++
    }
    
    fun even() {
        gamecount++
    } 
    
}
``` 

<br>

- Main : 실행

```kotlin
package com.company.p_10_strategy

fun main(args: Array<String>) {
    val player1 = Player("하나",WinningStrategy(1))
    val player2 = Player("두리",WinningStrategy(2))
    
    
    for (i in 0..10000){
        val hand1 = player1.nextHand()
        val hand2 = player2.nextHand()
        
        when(hand1.isStrongThan(hand2)){
            true -> {
                player1.win()
                player2.lose()
            }
            
            false ->{
                player1.lose()
                player2.win()
            }
            
            else -> {
                player1.even()
                player2.even()
            }
            
        }
    }
    
}
```