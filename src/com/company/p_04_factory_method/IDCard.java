package com.company.p_04_factory_method;

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
