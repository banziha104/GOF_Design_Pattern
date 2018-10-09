# Iterator 패턴 (반복자 패턴)

> 순서대로 지정하면서 전체를 실행

- 필요 클래스 및 인터페이스

| 이름              | 설명                                                                                                  |
|-------------------|-------------------------------------------------------------------------------------------------------|
| Iterator          | 요소를 순서대로 검색해가는 인터페이스를 결정함                                                        |
| ConcreteIterator  | Iterator가 결정한 인터페이스를 구현함                                                                 |
| Aggregate         | Iterator 역할을 만들어내는 인터페이스를 결정하고, 내가 가지고 있는 요소를 순서대로 검색해주는 객체임. |
| ConcreteAggregate | Aggregate 역할을 실제로 구현하는 일을 함.                                                             |


- 예제 클래스 

| 이름              | 해설                                                           | 필요 클래스 및 인터페이스 역할 |
|-------------------|----------------------------------------------------------------|--------------------------------|
| Aggregate         | 집합체를 나타내는 인터페이스                                   | Aggregate                      |
| BookShelf         | 서가를 나타내는 클래스, 검색 기능을 BookShelfIterator에게 위임 | ConcreteAggregate              |
| Iterator          | 하나씩 나열하면서 검색을 실행하는 인터페이스                   | Iterator                       |
| BookShelfIterator | 서가를 검색하는 클래스                                         | ConcreteIterator               |
| Book              | 책을나타내는 인터페이스                                        |                                |
| Main              | 실행하는 클래스                                                |                                |

- Aggreate 인터페이스 : 집약하는 클래스로 Iterator를 모아주는 역할을함

```java

public interface Aggrerate { // Iterator를 모으는 클래스
    Iterator iterator(); 
}

```

- Iterator 인터페이스 : 하나씩 나열하면서 루프 변수와 같은 역할을 수행함 

```java

public interface Iterator {
    boolean hasNext(); // 다음요소가 존재하는지
    Object next(); // 다음 요소를 반환, 다음 실행에 대비해 미리 내부 상태를 다음으로 진행시켜둠
}

```

- Book 클래스 : 반환되는 모델역할을 담당함

```java

public class Book {
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```


- BookShelf 클래스 : 직접적으로 Aggregate를 구현함으로써, 반복할 준비를 하는 객체 

```java

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

```

- BookShelfIterator 클래스 : Iterator를 구현받아 반복자 자체를 구현하는 객체

```java

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

```

- Main 클래스 

```java

public class Main {
    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf(4);

        bookShelf.appendBook(new Book("1번"));
        bookShelf.appendBook(new Book("2번"));
        bookShelf.appendBook(new Book("3번"));
        bookShelf.appendBook(new Book("4번"));

        Iterator it = bookShelf.iterator(); // 이터레이터를 생성

        while (it.hasNext()){ // hasNext를 불린 값으로 이용 
            Book book = (Book) it.next();
            System.out.println(book.getName());
        }
    }
}


```
