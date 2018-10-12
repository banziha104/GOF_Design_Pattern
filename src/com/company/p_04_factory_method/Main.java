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
