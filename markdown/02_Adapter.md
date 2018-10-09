# Adapter 패턴 

> 바꿔서 재이용하기

<br>

- 기존 클래스를 개조하거나, 목적한 인터페이스에 맞춤
- 버전업과 호환성에 용이함.    

<br>

### 필요클래스 및 인터페이스

| 이름    | 해설                                                               | 필요클래스 및 인터페이스 ㅕㄱ할 |
|---------|--------------------------------------------------------------------|---------------------------------|
| Target  | 지금 필요한 메소드를 결정함                                        | Print                           |
| Client  | 의뢰자 역할로, Target 역할의 메소드를 사용해 일을 처리함           | Main                            |
| Adaptee | 개조되는 쪽의 역할로, 이미 준비되어 있는 메소드를 가지고 있는 역할 | Banner                          |
| Adapter | Adaptee의 메소드를 최대한 활용해 Target 역할을 만족시킴            | PrintBanner                     |

<br>

- Banner 클래스 : 실제 출력이되는 클래스

```java

public class Banner {
    private String string;

    public Banner(String string) {
        this.string = string;
    }

    public void showWithParen() {
        System.out.println("(" + string + ")");
    }

    public void showWithAster() {
        System.out.println("*" + string + "*");
    }
}

```


<br>

- Print 인터페이스 : 어댑터에 필요한 메소드를 정의함

```java
public interface Print { // 어댑터에 필요한 객체를 정의함 
    void printWeak();
    void printString();
}
```

- PrintBanner 클래스 : 어댑터 역할을 하는 클래스

```java

public class PrintBanner extends Banner implements Print {

    public PrintBanner(String string) {
        super(string);
    }

    @Override
    public void printWeak() {
        showWithParen();
    }

    @Override
    public void printString() {
        showWithAster();
    }
}
```

- Main : 실행부

```java

public class Main {
    public static void main(String[] args){
        Print print = new PrintBanner("hello");
        print.printString();
        print.printWeak();
    }
}

```