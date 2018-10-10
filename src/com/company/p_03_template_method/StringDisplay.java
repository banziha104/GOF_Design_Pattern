package com.company.p_03_template_method;


/* 상속을 받아 구현*/
public class StringDisplay extends AbstractDisplay {
    private String string;
    private int width;

    public StringDisplay(String string) {
        this.string = string;
        this.width = string.getBytes().length;
    }

    @Override
    void open() {
        printLine();
    }

    @Override
    void print() {
        System.out.println("|" + string + "");
    }

    @Override
    void close() {
        printLine();
    }

    private void printLine(){
        System.out.print("+");
        for(int i = 0 ; i < width ; i++){
            System.out.print("-");
        }
        System.out.print("+");
    }
}
