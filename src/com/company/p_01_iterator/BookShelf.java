package com.company.p_01_iterator;

public class BookShelf implements Aggrerate {
    private Book[] books; // 실제 반복되는 객체
    private int last = 0; // 가장 Book[]의 가장 마지막 인덱스

    public BookShelf(int maxsize) {
        this.books = new Book[maxsize]; // 생성시 반복되는 객체의 최대 크기를 지정
    }

    public Book getBookAt(int index){
        return books[index];
    }

    public void appendBook(Book book){
        this.books[last] = book;
        last++;
    }

    public int getLength(){ // 최대길이를 반환
        return last;
    }
    @Override
    public Iterator iterator() {
        return new BookShelfIterator(this); // BookShelfIterator에 위임
    }
}
