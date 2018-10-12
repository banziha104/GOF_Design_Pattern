package com.company.p_04_factory_method;

import java.util.ArrayList;
import java.util.List;

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
