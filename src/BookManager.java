import java.time.LocalDate;
import java.util.Scanner;
//purchase history, change admins password

public class BookManager {
    static Integer generalId = 0;
    static Book[] books = new Book[10];
    static Sales[] sales = new Sales[10];
    static User user = Main.user;
    static {
        Book book1 = new Book();
        book1.setId(generalId++);
        book1.setTitle("Book 1");
        book1.setAuthor("John Doe");
        book1.setYear(1990);
        book1.setPrice(39990);
        books[0] = book1;
        Book book2 = new Book();
        book2.setId(generalId++);
        book2.setTitle("Book 2");
        book2.setAuthor("John Doe");
        book2.setYear(2000);
        book2.setPrice(39990);
        books[1] = book2;
        Sales sales1 = new Sales();
        sales1.setId(generalId++);
        sales1.setBook(book1);
        sales1.setUser(sales1.getUser());
        sales1.setDate(LocalDate.now().minusDays(1));
    }
    static Integer bookIndex = 2;
    static Integer salesIndex = 1;
    static Scanner scannerNum = new Scanner(System.in);
    static Scanner scannerStr = new Scanner(System.in);

    public void startAdmin() {
        while (true) {
            int n = adminMenu();
            switch (n) {
                case 1 -> addBook();
                case 2 -> editBook();
                case 3 -> deleteBook();
                case 4 -> showBookList();
                case 5 -> showUserList();
                case 6 -> showSalesList();
                case 7 -> changePassword();
                case 0 -> {
                    return;
                }

            }
        }
    }
        public void startUser () {
            while (true) {
                int n = userMenu();
                switch (n) {
                    case 1 -> searchBook();
                    case 2 -> showBookList();
                    case 3 -> buyBook();
                    case 4 -> purchaseHistory();
                    case 5 -> changePassword();
                    case 6 -> fillBalance();
                    case 0 -> {
                        return;
                    }

                }
            }
        }

    public void purchaseHistory() {
        if (salesIndex == 0) {
            System.out.println("У вас еще нет покупок.");
            return;
        }

        boolean found = false;
        for (int i = 0; i < salesIndex; i++) {
            Sales sale = sales[i]; // Получаем каждую покупку
            if (sale != null && sale.getUser().equals(Main.user.getId())) {
                Book purchasedBook = sale.getBook(); // Получаем книгу
            }
        }
        if (!found) {
            System.out.println("У вас еще нет покупок.");
        }
    }


    public int adminMenu () {
            String menu = """
                    *****BOOKSHOP (ADMIN)******
                    1.Add Book
                    2.Edit Book
                    3.Delete Book
                    4.Show Book List
                    5.Show User List
                    6.Show Sale List
                    7.Change Password
                    0.Exit
                    Enter Option:
                    """;
            System.out.print(menu);
            return scannerNum.nextInt();
        }
        public int userMenu () {
            String menu = """
                    *****BOOKSHOP******
                    1.Search Book
                    2.Show Book List
                    3.Buy Book
                    4.Purchase History
                    5.Change Password
                    6.Fill Balance
                    0.Exit
                    Enter Option:
                    """;
            System.out.print(menu);
            return scannerNum.nextInt();
        }
        public Book addBook () {
            System.out.println("Enter Book Title: ");
            String title = scannerStr.nextLine();
            for (Book book : books) {
                if (book != null && book.getTitle().equals(title)) {
                    return null;
                }
            }
            System.out.println("Enter Book Author: ");
            String author = scannerStr.nextLine();
            System.out.println("Enter Book Price: ");
            Integer price = scannerNum.nextInt();
            System.out.println("Enter Book year");
            Integer year = scannerNum.nextInt();

            Book newBook = new Book(generalId++, title, author, year, price);
            books[bookIndex++] = newBook;
            return newBook;
        }
        public void editBook () {
            showBookList();
            System.out.println("Choose book id to edit: ");
            Integer bookId = scannerNum.nextInt();
            for (Book book : books) {
                if (book.getId().equals(bookId)) {
                    System.out.println("Enter Book Title: ");
                    book.setTitle(scannerStr.nextLine());
                    System.out.println("Enter Book Author: ");
                    book.setAuthor(scannerStr.nextLine());
                    System.out.println("Enter Book Price: ");
                    book.setPrice(scannerNum.nextInt());
                    System.out.println("Enter Book year: ");
                    book.setYear(scannerNum.nextInt());
                    break;
                }
            }
            System.out.println("Incorrect Book ID");
        }

    public void deleteBook() {
        showBookList();
        System.out.println("Choose book id to delete: ");
        Integer bookId = scannerNum.nextInt();
        for (int i = 0; i < books.length; i++) {
            if (books[i].getId().equals(bookId)) {
                books[i] = null;
                break;
            }
        }
        System.out.println("Book deleted!");
    }

    public void showBookList() {
        for (Book book : books) {
            if (book != null) {
                System.out.println(book);
            }
        }
    }
    public void showUserList() {
        for (User user : User.users) {
            if (user != null) {
                System.out.println(user);
            }
        }
    }
    public void changePassword() {
        System.out.println("Enter Username");
        String username = scannerStr.nextLine();
        System.out.println("Enter Old Password: ");
        String oldPassword = scannerStr.nextLine();
        for (User user : User.users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(oldPassword)) {
                System.out.println("Enter New Password: ");
                String newPassword = scannerStr.nextLine();
                user.setPassword(newPassword);
                break;
            }
        }
        System.out.println("Password changed!");
    }
    public void searchBook() {
        System.out.println("Enter Book Title: ");
        String bookTitle = scannerStr.nextLine();
        for (Book book : books) {
            if (book != null &&book.getTitle().contains(bookTitle)) {
                System.out.println(book);
            }
        }
        System.out.println("No such Book Title");
    }
    public Sales buyBook() {
        showBookList();
        System.out.println("Choose book id to buy: ");
        Integer bookId = scannerNum.nextInt();

        for (Book book : books) {
            if (book != null && book.getId().equals(bookId)) { // Null check added
                if (Main.user.getBalance() < book.getPrice()) {
                    System.out.println("Insufficient balance.");
                    return null;
                }
                Main.user.setBalance(Main.user.getBalance() - book.getPrice());

                Sales sale = new Sales();
                sale.setId(generalId++);
                sale.setBook(book);
                sale.setUser(Main.user);
                sale.setDate(LocalDate.now());
                sales[salesIndex++] = sale;

                System.out.println("Book purchased successfully!");
                return sale;
            }
        }
        System.out.println("Invalid Book ID.");
        return null;
    }


    public void fillBalance() {
        System.out.println("Enter Username");
        String username = scannerStr.nextLine();
        for (User user : User.users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Enter amount ");
                Integer amount = scannerNum.nextInt();
                user.setBalance(user.getBalance() + amount);
                System.out.println("Balance changed!\n" +
                        " current balance: "+user.getBalance());
                break;
            }
        }
    }
    public void showSalesList() {
        for (Sales sale : sales) {
            if (sale != null) {
                System.out.println(sale);
            }
        }
    }
}







