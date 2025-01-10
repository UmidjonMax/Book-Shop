public class User {
    static User[] users = new User[10];
    static Integer userIndex = 0;
    static {
        User user = new User();
        user.setId(BookManager.generalId++);
        user.setName("John Doe");
        user.setSurname("Smith");
        user.setAge(49);
        user.setUsername("John Doe");
        user.setPassword("1234");
        user.setBalance(40000d);
        users[userIndex++] = user;
    }
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private String username;
    private String password;
    private Double balance = 0.0;

    public User(Integer id, String name, String surname, Integer age, String username, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.username = username;
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", balance=" + balance +
                '}';
    }
}
