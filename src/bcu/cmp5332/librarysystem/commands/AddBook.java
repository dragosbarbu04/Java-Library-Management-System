package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.data.BookDataManager;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.io.IOException;
import java.time.LocalDate;


public class AddBook implements  Command {

    private final String title;
    private final String author;
    private final String publicationYear;
    private final String publisher;
    private final boolean removed;

    public AddBook(String title, String author, String publicationYear, String publisher, boolean removed) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.removed = removed;
    }
    
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException, IOException {
        int maxId = 0;
    	if (library.getBooks().size() > 0) {
    		int lastIndex = library.getBooks().size() - 1;
            maxId = library.getBooks().get(lastIndex).getId();
    	}
        Book book = new Book(++maxId, title, author, publicationYear, publisher, false);
        library.addBook(book);
        System.out.println("Book #" + book.getId() + " added.");
        BookDataManager bookDataManager = new BookDataManager();
        bookDataManager.storeData(library);
    }
}
 