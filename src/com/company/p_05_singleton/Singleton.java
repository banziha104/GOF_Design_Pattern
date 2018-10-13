package com.company.p_05_singleton;

public class Singleton{
    private static Singleton ourInstance = new Singleton();

    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
        System.out.println("싱글톤 생성");
    }
}
