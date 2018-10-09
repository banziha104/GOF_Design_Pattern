package com.company.p_01_iterator;

public class Main {

    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf(4);

        bookShelf.appendBook(new Book("1번"));
        bookShelf.appendBook(new Book("2번"));
        bookShelf.appendBook(new Book("3번"));
        bookShelf.appendBook(new Book("4번"));

        Iterator it = bookShelf.iterator(); // 이터레이터를 생성

        while (it.hasNext()){
            Book book = (Book) it.next();
            System.out.println(book.getName());
        }
    }
}
