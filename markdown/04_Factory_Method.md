# 팩토리 메소드 패턴 

> 인스턴스를 만드는 방법을 상위 클래스에서 정의하고, 구체적인 내용은 하위에서 진행함

<br>

- ### 예제 클래스

| 이름          | 해설                                                          | 대응 클래스 |
|---------------|---------------------------------------------------------------|-------------|
| Product       | 추상클래스 use만 정의되어 있는 추상클래스                     |             |
| Factory       | 메소드 create를 구현하고 있는 추상 클래스                     |             |
| IDCard        | 메소드 use를 구현하고 있는 클래스                             |             |
| IDCardFactory | 메소드 createProduct , registerProduct를 구현하고 있는 클래스 |             |
| Main          | 실행담당                                                      |             |


- Product 클래스 : use만 정의하고 Product의 하위클래스에 위임

```java

public abstract class Product {
    abstract void use();
}

```