package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.data.LoanDataManager;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

import java.io.IOException;
import java.time.LocalDate;

public class ReturnBook implements Command{

    private final Integer bookId;
    private final Integer patronId;

    public ReturnBook(Integer bookId, Integer patronId) {
        this.bookId = bookId;
        this.patronId = patronId;
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException, IOException {
        Book book = library.getBookByID(bookId);
        Patron patron = library.getPatronByID(patronId);
        if (book != null || book.isOnLoan()) {
            library.removeLoan(book.getLoan());
            patron.returnBook(book);
            System.out.println(patron.getName() + " has returned the book " + book.getTitle() + ".");
            LoanDataManager loanDataManager = new LoanDataManager();
            loanDataManager.storeData(library);
        }
        else {
            throw new LibraryException("Book is not on loan.");
        }
    }
}
