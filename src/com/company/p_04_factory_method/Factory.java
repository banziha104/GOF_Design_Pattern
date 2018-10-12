package com.company.p_04_factory_method;

public abstract class Factory {
    public final  Product create(String owner){
        Product p = createProduct(owner); // 제품을 만듬
        registerProduct(p); // 제품을 등록
        return p;
    }

    abstract Product createProduct(String owner);
    abstract void registerProduct(Product product);
}
