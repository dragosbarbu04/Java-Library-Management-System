package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.data.PatronDataManager;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.io.IOException;
import java.time.LocalDate;

public class AddPatron implements Command {

    private final String name;
    private final String phone;
    private final String email;

    public AddPatron(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException, IOException {
        int maxId = 0;
        if (!library.getPatrons().isEmpty()) {
            int lastIndex = library.getPatrons().size() - 1;
            maxId = library.getPatrons().get(lastIndex).getId();
        }
        Patron patron = new Patron(++maxId, name, phone, email, false);
        library.addPatron(patron);
        System.out.println("Patron #" + patron.getId() + " added.");
        PatronDataManager patronDataManager = new PatronDataManager();
        patronDataManager.storeData(library);
    }
}
