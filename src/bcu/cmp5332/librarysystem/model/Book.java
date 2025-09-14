package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

public class Book {
    
    private int id;
    private String title;
    private String author;
    private String publicationYear;
    private String publisher;

    private Loan loan;
    private boolean removed;

    public Book(int id, String title, String author, String publicationYear, String publisher, boolean removed) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.removed = removed;
    }

    public int getId() {
        return id;
    } 

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }
	
    public String getDetailsShort() {
        return "Book #" + id + " - " + title;
    }

    public String getDetailsLong() {
        return "Book #" + id + " - " + title + ", Author: " + author + ", Year: " + publicationYear + ", Publisher: " + publisher + ", Hidden: " + removed;
    }

    @Override
    public String toString() {
        return title;
    }

    public boolean isOnLoan() {
        return (loan != null);
    }

    public String getStatus() {
        if (isOnLoan()) {
            return "On loan until " + getDueDate().toString();
        } else {
            return "Available";
        }
    }

    public LocalDate getDueDate() {
        if (loan != null) {
            return loan.getDueDate();
        }
        return null;
    }

    public void setDueDate(LocalDate dueDate) throws LibraryException {
        if (loan != null) {
            loan.setDueDate(dueDate);
        } else {
            throw new LibraryException("Book is not on loan");
        }
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public void returnToLibrary() {
        loan = null;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public boolean isHidden() {
        return removed;
    }

    public void setHidden(boolean removed) {
        this.removed = removed;
    }

}
