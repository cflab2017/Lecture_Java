public class Document implements Printable {
    private String text;
    public Document(String t) { this.text = t; }

    @Override
    public void print() {
        System.out.println(">> " + text);
    }

    public static void main(String[] args) {
        Printable p = new Document("Hello, Interface!");
        p.print();
    }
}
