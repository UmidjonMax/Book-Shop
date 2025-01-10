import java.time.LocalDate;

public class Sales {
    private Integer id;
    private Book book;
    private User user;
    private LocalDate date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userId) {
        this.user = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "id=" + id +
                ", book=" + book +
                ", user=" + user +
                ", date=" + date +
                '}';
    }
}
