package gof.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Iterator Pattern (이터레이터 패턴)
 * 
 * 목적: 컬렉션의 내부 구현 방식을 노출하지 않고도 그 집합체 안에 들어있는 
 * 모든 원소들에 순차적으로 접근할 수 있는 방법을 제공합니다.
 */

// 1. 아이템 레코드 (Java 17 Record 사용)
record Book(String title) {}

// 2. 이터레이터 인터페이스
interface MyIterator<T> {
    boolean hasNext();
    T next();
}

// 3. 집합체 인터페이스
interface MyAggregate<T> {
    MyIterator<T> createIterator();
}

// 4. 구체적인 집합체
class BookShelf implements MyAggregate<Book> {
    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) { books.add(book); }

    @Override
    public MyIterator<Book> createIterator() {
        return new BookShelfIterator(this);
    }

    public int getLength() { return books.size(); }
    public Book getBookAt(int index) { return books.get(index); }
}

// 5. 구체적인 이터레이터
class BookShelfIterator implements MyIterator<Book> {
    private final BookShelf bookShelf;
    private int index = 0;

    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
    }

    @Override
    public boolean hasNext() {
        return index < bookShelf.getLength();
    }

    @Override
    public Book next() {
        return bookShelf.getBookAt(index++);
    }
}

class IteratorMain {
    public static void main(String[] args) {
        BookShelf shelf = new BookShelf();
        shelf.addBook(new Book("Design Patterns"));
        shelf.addBook(new Book("Clean Code"));
        shelf.addBook(new Book("Java 17 in Action"));

        MyIterator<Book> it = shelf.createIterator();
        while (it.hasNext()) {
            System.out.println("책 제목: " + it.next().title());
        }
    }
}
