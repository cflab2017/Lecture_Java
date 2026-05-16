/** Book 클래스를 정의하고 info() 출력. */
public class Homework01 {
    public static void main(String[] args) {
        Book a = new Book("Effective Java", "Joshua Bloch", 384);
        Book b = new Book("Clean Code", "Robert C. Martin", 464);
        System.out.println(a.info());
        System.out.println(b.info());
    }
}

class Book {
    String title;
    String author;
    int pages;

    Book(String title, String author, int pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    String info() {
        return "[" + title + "] " + author + "(" + pages + "쪽)";
    }
}
