# Singleton

> 한 개의 인스턴스만 만들기

<br>

- ### 필요클래스


| 이름      | 해설                             |
|-----------|----------------------------------|
| Singleton | 인스턴스가 1개만 존재하는 클래스 |
| Main      | 실행클래스                       | 


<br>

- Singleton 

```java
public class Singleton {
    private static Singleton ourInstance = new Singleton();

    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
        System.out.println("싱글톤 생성");
    }
}
```

<br>

- Main 

```java
package com.company.p_05_singleton;

public class Main {
    public static void main(String[] args){
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        
        if(s1 == s2){
            System.out.println("같은 인스턴스");
        }
    }
}

```