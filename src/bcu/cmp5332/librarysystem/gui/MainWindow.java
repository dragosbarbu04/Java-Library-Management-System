package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class MainWindow extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu adminMenu;
    private JMenu booksMenu;
    private JMenu membersMenu;

    private JMenuItem adminExit;

    private JMenuItem booksView;
    private JMenuItem booksAdd;
    private JMenuItem booksDel;
    private JMenuItem booksIssue;
    private JMenuItem booksReturn;
    private JMenuItem memViewLoanedBooks;

    private JMenuItem memView;
    private JMenuItem memAdd;
    private JMenuItem memDel;

    private Library library;
    private JMenuItem booksViewInfo;
    private JTable booksTable;
    private JTable patronsTable;


    public MainWindow(Library library) {

        initialize();
        this.library = library;
    }

    public Library getLibrary() {
        return library;
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Library Management System");

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //adding adminMenu menu and menu items
        adminMenu = new JMenu("Admin");
        menuBar.add(adminMenu);

        adminExit = new JMenuItem("Exit");
        adminMenu.add(adminExit);
        adminExit.addActionListener(this);

        // adding booksMenu menu and menu items
        booksMenu = new JMenu("Books");
        menuBar.add(booksMenu);

        booksView = new JMenuItem("View");
        booksAdd = new JMenuItem("Add");
        booksDel = new JMenuItem("Delete");
        booksIssue = new JMenuItem("Issue");
        booksReturn = new JMenuItem("Return");
        booksMenu.add(booksView);
        booksMenu.add(booksAdd);
        booksMenu.add(booksDel);
        booksMenu.add(booksIssue);
        booksMenu.add(booksReturn);
        for (int i = 0; i < booksMenu.getItemCount(); i++) {
            booksMenu.getItem(i).addActionListener(this);
        }

        // adding membersMenu menu and menu items
        membersMenu = new JMenu("Members");
        menuBar.add(membersMenu);

        memView = new JMenuItem("View");
        memAdd = new JMenuItem("Add");
        memDel = new JMenuItem("Delete");

        membersMenu.add(memView);
        membersMenu.add(memAdd);
        membersMenu.add(memDel);

        memViewLoanedBooks = new JMenuItem("View Loaned Books");
        membersMenu.add(memViewLoanedBooks);
        memViewLoanedBooks.addActionListener(this);

        memView.addActionListener(this);
        memAdd.addActionListener(this);
        memDel.addActionListener(this);

        booksViewInfo = new JMenuItem("View Info");
        booksMenu.add(booksViewInfo);
        booksViewInfo.addActionListener(this);

        setSize(800, 500);

        setVisible(true);
        setAutoRequestFocus(true);
        toFront();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
/* Uncomment the following line to not terminate the console app when the window is closed */
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

/* Uncomment the following code to run the GUI version directly from the IDE */
//    public static void main(String[] args) throws IOException, LibraryException {
//        Library library = LibraryData.load();
//        new MainWindow(library);			
//    }



    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == adminExit) {
            System.exit(0);
        } else if (ae.getSource() == booksView) {
            displayBooks();

        } else if (ae.getSource() == booksAdd) {
            new AddBookWindow(this);

        } else if (ae.getSource() == booksDel) {


        } else if (ae.getSource() == booksIssue) {


        } else if (ae.getSource() == booksReturn) {


        } else if (ae.getSource() == memView) {
            displayPatrons();

        } else if (ae.getSource() == memAdd) {
            new AddPatronWindow(this);

        } else if (ae.getSource() == memDel) {

        }
        else if (ae.getSource() == booksViewInfo) {
            viewBookInfo();
        }
        else if (ae.getSource() == memViewLoanedBooks) {
            displayLoanedBooks();
        }
    }
    List<Integer> bookIds = new ArrayList<>();
    List<Integer> patronIds = new ArrayList<>();

    private Book getSelectedBook() {

        int selectedRowIndex = booksTable.getSelectedRow();
        if (selectedRowIndex >= 0 && selectedRowIndex < bookIds.size()) {
            int bookId = bookIds.get(selectedRowIndex); // Get the ID from the list

            for (Book book : library.getBooks()) {
                if (book.getId() == bookId) {
                    return book;
                }
            }
        }
        return null; // No book is selected or ID not found
    }
    private Patron getSelectedPatron() {
        int selectedRowIndex = patronsTable.getSelectedRow();
        if (selectedRowIndex >= 0 && selectedRowIndex < patronIds.size()) {
            int patronId = patronIds.get(selectedRowIndex); // Get the ID from patronIds

            for (Patron patron : library.getPatrons()) {
                if (patron.getId() == patronId) {
                    return patron;
                }
            }
        }
        return null; // No patron is selected or ID not found
    }


    public void displayBooks() {
        List<Book> booksList = library.getBooks();
        String[] columns = new String[]{"Title", "Author", "Publication Year", "Publisher", "Status"};
        Object[][] data = new Object[booksList.size()][columns.length];
        bookIds.clear(); // Clear the previous IDs

        for (int i = 0; i < booksList.size(); i++) {
            Book book = booksList.get(i);
            bookIds.add(book.getId()); // Store the ID
            data[i][0] = book.getTitle();
            data[i][1] = book.getAuthor();
            data[i][2] = book.getPublicationYear();
            data[i][3] = book.getPublisher();
            data[i][4] = book.getStatus();
        }

        booksTable = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(booksTable);
        this.getContentPane().removeAll();
        this.getContentPane().add(scrollPane);
        this.revalidate();
    }


    public void displayPatrons() {
        List<Patron> patronsList = library.getPatrons();
        String[] columns = new String[]{"Name", "Phone", "Email", "Borrowed Books"};
        Object[][] data = new Object[patronsList.size()][4];

        patronIds.clear(); // Clear existing IDs

        for (int i = 0; i < patronsList.size(); i++) {
            Patron patron = patronsList.get(i);
            patronIds.add(patron.getId()); // Add patron ID to the list

            data[i][0] = patron.getName();
            data[i][1] = patron.getPhone();
            data[i][2] = patron.getEmail();
            data[i][3] = patron.getBooks().size(); // Number of borrowed books
        }

        patronsTable = new JTable(data, columns); // Update the patronsTable
        JScrollPane scrollPane = new JScrollPane(patronsTable);
        this.getContentPane().removeAll();
        this.getContentPane().add(scrollPane);
        this.revalidate();
    }


    private void viewBookInfo() {
        // Example: Assume you have a way to get the selected book.
        // This might be from a JTable or another component.
        Book selectedBook = getSelectedBook(); // You need to implement this method
        if (selectedBook == null) {
            JOptionPane.showMessageDialog(this, "No book selected", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String info = "Title: " + selectedBook.getTitle() +
                "\nAuthor: " + selectedBook.getAuthor() +
                "\nPublication Year: " + selectedBook.getPublicationYear() +
                "\nStatus: " + (selectedBook.isOnLoan() ? "On loan" : "Available");

        if (selectedBook.isOnLoan()) {
            info += "\nLoaned to: " + selectedBook.getLoan().getPatron().getName() +
                    "\nDue Date: " + selectedBook.getLoan().getDueDate();
        }

        JOptionPane.showMessageDialog(this, info, "Book Information", JOptionPane.INFORMATION_MESSAGE);
    }
    private void displayLoanedBooks() {
        Patron selectedPatron = getSelectedPatron();
        if (selectedPatron == null) {
            JOptionPane.showMessageDialog(this, "No patron selected", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Book> loanedBooks = selectedPatron.getBooks(); // Assuming Patron has a getBooks method for loaned books
        if (loanedBooks.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Patron has no books on loan", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder info = new StringBuilder("Loaned Books:\n");
        for (Book book : loanedBooks) {
            info.append("Title: ").append(book.getTitle()).append(", ")
                    .append("Author: ").append(book.getAuthor()).append(", ")
                    .append("Due Date: ").append(book.getLoan().getDueDate()).append("\n");
        }

        JOptionPane.showMessageDialog(this, info.toString(), "Loaned Books", JOptionPane.INFORMATION_MESSAGE);
    }
}
