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

<br> 

- ### 필요 클래스 

| 이름            | 해설                                                                                                                                | 예제상의 클래스 |
|-----------------|--------------------------------------------------------------------------|-----------------|
| Product         | 이 패턴에서 생성되는 인스턴스가 가져야 할 인터페이스를 결정하는 추상클래스                                                          | Product         |
| Creator         | 인스턴스를 생성하는 대신에, 인스턴스 생성을 위한 메소드를 호출해서 <br> 구체적인 클래스 이름에 의한 속박에서 상위 클래스를 자유롭게 만듬 | Factory         |
| ConcreteProduct | 구체적인 제품을 결정                                                                                                                | Factory         |
| ConcreteCreator | 구체적인 제품을 만드는 클래스                                                                                                       | IDCardFactory   |


<br>


- Product 클래스 : use만 정의하고 Product의 하위클래스에 위임

```java

public abstract class Product {
    abstract void use();
}

``` 

- Factory : 하위 클래스에 생성과 등록을 위임함

```java

public abstract class Factory {
    public final  Product create(String owner){
        Product p = createProduct(owner); // 제품을 만듬
        registerProduct(p); // 제품을 등록 
        return p;
    }
    
    abstract Product createProduct(String owner);
    abstract void registerProduct(Product product);
}

``` 

- IDCard : 제품을 구현하는 클래스 

```java

public class IDCard extends Product {
    private String owner;

    public IDCard(String owner) {
        this.owner = owner;
    }

    @Override
    void use() {
        System.out.println(owner+"의 카드를 사용");
    }
    
    public String getOwner() {
        return owner;
    }
}

``` 


- IDCardFactory : 제품을 만들어내는 클래스

```java

public class IDCardFactory extends Factory {
    private List owners = new ArrayList();
    
    @Override
    Product createProduct(String owner) { // 하위에서 직접 구현
        return new IDCard(owner);
    }

    @Override
    void registerProduct(Product product) {
        owners.add(((IDCard)product).getOwner());
    }
    
    public List getOwners(){
        return getOwners();
    }
}

``` 

- Main : 실행

```java
package com.company.p_04_factory_method;

public class Main {
    public static void main(String[] args){
        Factory factory = new IDCardFactory();
        Product card1 = factory.createProduct("11");
        Product card2 = factory.createProduct("22");
        Product card3 = factory.createProduct("33");
        card1.use();
        card2.use();
        card3.use();
    }
}

```