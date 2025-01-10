import java.util.Scanner;

public class Main {
    static Scanner scnum = new Scanner(System.in);
    static Scanner scstr = new Scanner(System.in);
    static BookManager bsm = new BookManager();
    static User user = new User();
    public static void main(String[] args) {
        while (true) {
            int n = menu();
            switch (n) {
                case 1 -> logIn();
                case 2 -> register();
                case 0 -> {
                    return;
                }
            }
        }
    }
    public static int menu(){
        System.out.print("""
                *****WELCOME*****
                1.Log In
                2.Register
                0.Exit
                Choose option:
                """);
        return scnum.nextInt();
    }
    public static void logIn(){
        Admin admin = new Admin();
        System.out.println("Enter Username:");
        String username = scstr.nextLine();
        System.out.println("Enter Password:");
        String password = scstr.nextLine();
        if (admin.getUsername().equals(username) && admin.getPassword().equals(password)){
            bsm.startAdmin();
        }else if(user.getUsername().equals(username) && user.getPassword().equals(password)){
            bsm.startUser();
        }else {
            System.out.println("Incorrect Username or Password");
        }
    }
    public static void register(){
        System.out.print("Enter your name:");
        user.setName(scstr.nextLine());
        System.out.print("Enter your surname:");
        user.setSurname(scstr.nextLine());
        System.out.print("Enter your age:");
        user.setAge(scnum.nextInt());
        System.out.print("Enter your username:");
        user.setUsername(scstr.nextLine());
        System.out.print("Enter your password:");
        user.setPassword(scstr.nextLine());
        User.users[User.userIndex++] = user;
        System.out.println("Registered Successfully");
        bsm.startUser();
    }
}