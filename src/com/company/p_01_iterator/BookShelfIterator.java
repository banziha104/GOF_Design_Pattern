package com.company.p_01_iterator;

public class BookShelfIterator implements Iterator {
    private BookShelf bookShelf; // Bookshelf 객체를 받아옮
    private int index;

    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        this.index = 0; // 생성시 인덱스 초기화
    }

    @Override
    public boolean hasNext() {
        if(index < bookShelf.getLength()) return true; // 인덱스가 최대 보다 작은경우, 다음이 있다가정
        else return false;
    }

    @Override
    public Object next() {
        Book book = bookShelf.getBookAt(index); // BookShelf에서 해당 인덱스에 맞는 책을 리턴
        index++;
        return book;
    }
}
