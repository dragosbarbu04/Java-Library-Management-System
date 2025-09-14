package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;
import java.util.List;

public class ListPatrons implements Command {

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        List<Patron> patrons = library.getPatrons();
        int i = 0;
        for (Patron patron : patrons) {
            if (!patron.isRemoved()) {
                System.out.println(patron.getDetailsShort());
            } else {
                i++;
            }
        }
        System.out.println((patrons.size() - i) + " patron(s)");
    }
}