package com.company.p_03_template_method;

public class Main {
    public static void main(String[] args){
            AbstractDisplay d1 =  new CharDisplay('H');
            AbstractDisplay d2 = new StringDisplay("hello world");

            d1.display(); // 하위 클래스의 정의에 따라 달라짐
            d2.display();
    }
}
