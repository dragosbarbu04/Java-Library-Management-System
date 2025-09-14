package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.data.BookDataManager;
import bcu.cmp5332.librarysystem.data.PatronDataManager;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;

import java.io.IOException;
import java.time.LocalDate;

public class HideBook implements Command{

    private final Integer bookID;

    public HideBook(Integer bookID) {
        this.bookID = bookID;
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException, IOException {
        Book book = library.getBookByID(bookID);
        book.setHidden(!book.isHidden());
        BookDataManager bookDataManager = new BookDataManager();
        bookDataManager.storeData(library);
    }
}
