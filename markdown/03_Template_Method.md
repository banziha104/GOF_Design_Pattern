# 템플릿 메소드 

> 하위 클래스에서 구체적으로 처리하기 

- 상위 클래스쪽에 템플릿에 해당하는 메서드가 정의되어 있음 (추상 메소드)
- 최종적으로는 하위 클래스를 실제로 구현할 수 있음.
- 로직을 공통화할 수 있음 

<br>

### 필요 클래스

| 이름          | 해설                                                        | 예제 클래스                |
|---------------|-------------------------------------------------------------|----------------------------|
| AbstractClass | 템플릿 메소드는 구현하고 추상 메소드는 하위 클래스에 위임함 | AbstractDisplay            |
| ConcreteClass | 추상 메소드를 구체적으로 구현함                             | StringDisplay, CharDisplay |


<br>

### 예제 클래스
| 이름            | 해설                                             | 필요클래스 및 인터페이스 역할 |
|-----------------|--------------------------------------------------|-------------------------------|
| AbstractDisplay | 메소드 Display만 구현되고 있는 추상클래스        | AbstractClass                            |
| CharDisplay     | 메소드 open, print, close를 구현하고 있는 클래스 | ConcreteClass                          |
| StringDisplay   | 메소드 open, print, close를 구현하고 있는 클래스 | ConcreteClass                            |
| Main            | 실행담당                                         |  메인                            |

<br>

- #### AbstractDisplay : 추상 클래스로 하위 클래스에서 추상 메소드를 구현할 수 있도록 정의함

```java
package com.company.p_03_template_method;

public abstract class AbstractDisplay {
    /*하위 클래스에 위임*/
    abstract void open();
    abstract void print();
    abstract void close();

    // 해당 부분은 고정
    public final void display(){
        open();
        for (int i = 0 ; i < 5 ; i++){
            print();
        }
        close();
    }
}

```

<br>

- #### CharDisplay : 하위클래스로 상위 클래스에서 정의한 메소드를 구현

```java
package com.company.p_03_template_method;

public abstract class AbstractDisplay {
    /*하위 클래스에 위임*/
    abstract void open();
    abstract void print();
    abstract void close();

    // 해당 부분은 고정
    public final void display(){
        open();
        for (int i = 0 ; i < 5 ; i++){
            print();
        }
        close();
    }
}

```

<br>

- #### StringDisplay : 하위클래스로 상위 클래스에서 정의한 메소드를 구현 

```java
package com.company.p_03_template_method;


/* 상속을 받아 구현*/
public class StringDisplay extends AbstractDisplay {
    private String string;
    private int width;

    public StringDisplay(String string) {
        this.string = string;
        this.width = string.getBytes().length;
    }

    @Override
    void open() {
        printLine();
    }

    @Override
    void print() {
        System.out.println("|" + string + "");
    }

    @Override
    void close() {
        printLine();
    }

    private void printLine(){
        System.out.print("+");
        for(int i = 0 ; i < width ; i++){
            System.out.print("-");
        }
        System.out.print("+");
    }
}
```

- #### 메인 클래스 실행

```java
package com.company.p_03_template_method;

public class Main {
    public static void main(String[] args){
            AbstractDisplay d1 =  new CharDisplay('H');
            AbstractDisplay d2 = new StringDisplay("hello world");

            d1.display(); // 하위 클래스의 정의에 따라 달라짐
            d2.display();
    }
}
```