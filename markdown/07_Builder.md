# Builder

> 복잡한 인스턴스 조립하기

<br>

- ### 필요클래스


<br>

- ### 예제 클래스

| 이름        | 해설                                              | 대응클래스 |
|-------------|---------------------------------------------------|------------|
| Builder     | 문서를 구성하기 위한 메소드를 결정하는 추상클래스 | ㄴ         |
| Director    | 한개의 문서를 만드는 클래스                       | s          |
| TextBuilder | 일반 텍스트를 이용해서 문서를 만드는 클래스       | s          |
| HTMLBuilder | HTML 파일을 이용해서 문서를 만드는 클래스         | s          |
| Main        | 실행 담당                                         | s          |

<br>

- Builder : 문서를 만들 메소드들을 선언하고 있는 추상클래스

```kotlin

// 문서를 만들 메소드들을 선언하고 있는 추상클래스
abstract class Builder{
    abstract fun makeTitle(title : String)
    abstract fun makeString(stirng : String)
    abstract fun close()
}

``` 

<br>

- Director : 메소드를 이용해