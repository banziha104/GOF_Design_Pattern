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
