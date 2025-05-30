package management;

import datastructures.maps.CustomHashMap;
import library.Book;
import library.Member;

public class BookManager {
    private CustomHashMap<String, Book> books;
    private MemberManager memberManager;

    public BookManager(MemberManager memberManager) {
        books = new CustomHashMap<>();
        this.memberManager = memberManager;
    }

    public void addBook(Book book) {
        books.put(book.getIsbn(), book);
    }

    public Book getBookByIsbn(String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            throw new IllegalArgumentException("Book with ISBN " + isbn + " not found.");
        }
        return book;
    }

    public boolean isBookAvailable(String isbn) {
        Book book = getBookByIsbn(isbn);
        return book.isAvailable();
    }

    public void setBookAvailability(String isbn, boolean available) {
        Book book = getBookByIsbn(isbn);
        book.setAvailable(available);
    }

    public void addToWaitlist(String isbn, String memberId) {
        Book book = getBookByIsbn(isbn);
        Member member = memberManager.getMember(memberId);
        if (member == null) {
            throw new IllegalArgumentException("Member with ID " + memberId + " not found.");
        }
        book.addToWaitlist(member);
    }

    public Member getNextFromWaitlist(String isbn) {
        Book book = getBookByIsbn(isbn);
        return book.getNextInWaitlist();
    }

    public boolean hasWaitlist(String isbn) {
        Book book = getBookByIsbn(isbn);
        return book.hasWaitlist();
    }
}
