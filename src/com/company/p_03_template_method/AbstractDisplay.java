package com.company.p_03_template_method;

public abstract class AbstractDisplay {
    /*하위 클래스에 위임*/
    abstract void open();
    abstract void print();
    abstract void close();

    // 해당 부분은 고정
    public final void display(){
        open();
        for (int i = 0 ; i < 5 ; i++){
            print();
        }
        close();
    }
}
