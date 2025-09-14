import bcu.cmp5332.librarysystem.model.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class BookTest {

    @Test
    public void testBookHasPublisher() {
        Book book = new Book(0, "TestTitle", "TestAuthor", "TestYear", "TestPublisher", false);
        assertNotNull(book.getPublisher());
        book.setPublisher("Test Publisher");
        assertEquals("Test Publisher", book.getPublisher());
    }
}