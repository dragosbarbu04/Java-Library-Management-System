package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.data.LoanDataManager;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

import java.io.IOException;
import java.time.LocalDate;

public class RenewLoan implements Command{
    private final Integer bookId;
    private final Integer patronId;

    public RenewLoan(Integer bookId, Integer patronId){
        this.bookId = bookId;
        this.patronId = patronId;
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException, IOException {
        Book book = library.getBookByID(bookId);
        Patron patron = library.getPatronByID(patronId);
        if (!book.isOnLoan()) {
            throw new LibraryException("Book is not on loan.");
        } else {
            patron.renewBook(book, book.getDueDate().plusDays(3));
            System.out.println(book.getTitle() + "'s loan has been extended by 3 days for " + patron.getName() + ".");
            LoanDataManager loanDataManager = new LoanDataManager();
            loanDataManager.storeData(library);
        }
    }
}
