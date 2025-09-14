package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.data.PatronDataManager;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.model.Library;

import java.io.IOException;
import java.time.LocalDate;

public class HidePatron implements Command{

    private final Integer patronID;

    public HidePatron(Integer patronID) {
        this.patronID = patronID;
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException, IOException {
        Patron patron = library.getPatronByID(patronID);
        patron.setRemoved(!patron.isRemoved());
        PatronDataManager patronDataManager = new PatronDataManager();
        patronDataManager.storeData(library);
    }
}
