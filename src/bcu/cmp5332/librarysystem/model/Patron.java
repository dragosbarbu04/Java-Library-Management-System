package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Patron {

    private int id;
    private String name;
    private String phone;
    private String email;
    private boolean removed;
    private final int maxLoans = 3;
    private final List<Book> books = new ArrayList<>();

    public Patron(int id, String name, String phone, String email, boolean removed) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.removed = removed;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void borrowBook(Book book, LocalDate dueDate) throws LibraryException {
        if (books.contains(book)) {
            throw new LibraryException("This book is already borrowed by the patron.");
        }
        if (books.size() >= maxLoans) {
            throw new LibraryException("This patron has too many books loaned.");
        } else {
            books.add(book);
            book.setLoan(new Loan(this, book, LocalDate.now(), dueDate));
        }
    }

    public void renewBook(Book book, LocalDate newDueDate) throws LibraryException {
        if (!books.contains(book)) {
            throw new LibraryException("This book is not borrowed by the patron.");
        }
        Loan loan = book.getLoan();
        if (loan == null) {
            throw new LibraryException("No loan information found for this book.");
        }
        loan.setDueDate(newDueDate);
    }

    public void returnBook(Book book) throws LibraryException {
        if (!books.remove(book)) {
            throw new LibraryException("This book is not borrowed by the patron.");
        }
        book.returnToLibrary();
    }

    public String getDetailsLong() {
        return "#" + id + " - " + name + " - " + phone + " - " + email + " - Hidden: " + isRemoved() + "\nBorrowed books " + "(no. " + books.size() + "): " + books.toString();

    }

    public String getDetailsShort() {
        return name + " - " + phone;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
