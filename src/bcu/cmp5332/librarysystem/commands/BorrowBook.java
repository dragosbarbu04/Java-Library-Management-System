package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.data.LoanDataManager;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Patron;

import java.io.IOException;
import java.time.LocalDate;

public class BorrowBook implements Command {
    private final Integer bookId;
    private final Integer patronId;

    public BorrowBook(Integer bookId, Integer patronId) {
        this.bookId = bookId;
        this.patronId = patronId;
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException, IOException {
        Book book = library.getBookByID(bookId);
        if (book == null || book.isOnLoan()) {
            throw new LibraryException("Book is not available.");
        }

        Patron patron = library.getPatronByID(patronId);
        if (patron == null) {
            throw new LibraryException("Patron not found.");
        }

        LocalDate dueDate = LocalDate.now().plusDays(library.getLoanPeriod());
        Loan loan = new Loan(patron, book, LocalDate.now(), dueDate);
        book.setLoan(loan);
        library.updateBook(book);
        patron.borrowBook(book, dueDate);
        library.addLoan(loan);
        System.out.println(patron.getName() + " borrowed the book " + book.getTitle() + ".");
        LoanDataManager loanDataManager = new LoanDataManager();
        loanDataManager.storeData(library);
    }
}
